package com.crow.webmagic.pageprocessor;

import java.util.Date;
import java.util.List;
import java.util.Random;
import org.ansj.splitWord.analysis.ToAnalysis;
import com.crow.utils.ProxyGeneratedUtil;
import com.crow.utils.UserAgentUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by CrowHawk on 17/10/11.
 */
public class HupuBxjPageProcessor implements PageProcessor {//修改改类，定制自己的抽取逻辑

    public static final String URL_LIST = "https://bbs\\.hupu\\.com/bxj-\\d+";
    public static final String URL_POST = "https://bbs\\.hupu\\.com/\\d+\\-\\d+.html";
    public static final String URL_POST_1 = "https://bbs\\.hupu\\.com/\\d+\\.html";
    public static final String URL_USER = "https://my\\.hupu\\.com/\\d+";
    public static final int URL_LENGTH = "https://bbs.hupu.com/bxj-".length();
    private static final String ORDER_NUM = "ZF201710169692T66jkr";//讯代理订单号
    private static final String SECRET = "3b23ace31a2447baa44d624e9c5fd0f5";//讯代理密码

    //抓取网站的相关配置，包括编码、抓取间隔、重试次数、代理、UserAgent等
    private Site site = Site.me()
            //.addHeader("Proxy-Authorization",ProxyGeneratedUtil.authHeader(ORDER_NUM, SECRET, (int) (new Date().getTime()/1000)))//设置代理
            .setDisableCookieManagement(true)
            .setCharset("UTF-8")
            .setTimeOut(30000)
            .setRetryTimes(3)
            .setSleepTime(new Random().nextInt(20)*100)
            .setUserAgent(UserAgentUtil.getRandomUserAgent());

    @Override
    public void process(Page page) {

        //列表页
        if (page.getUrl().regex(URL_LIST).match()) {
            crawlList(page);
        }
        //主题帖第一页
        if (page.getUrl().regex(URL_POST_1).match()) {
            //page.addTargetRequests(URLGeneratedUtil.generatePostURLs(URL_POST_1));
            crawlPost(page);
        }
        //主题帖第一页以后
        if (page.getUrl().regex(URL_POST).match()) {
            crawlPost(page);
        }
        //用户页
        if (page.getUrl().regex(URL_USER).match()) {
            crawlUser(page);
        }

    }

    @Override
    public Site getSite() {
        //Set<Integer> acceptStatCode = new HashSet<>();
        //acceptStatCode.add(200);
        //site = site.setAcceptStatCode(acceptStatCode).addHeader("Accept-Encoding", "/");

        return site;
    }

    /**
     * 抓取论坛列表页信息
     * @param page 当前页面对象
     */
    private void crawlList(Page page) {//抓取论坛列表页
        /*List<String> listUrls = page.getHtml().links().regex("/\\d+\\.html").all();
        listUrls.forEach(e -> URLGeneratedUtil.generatePostURL(e));
        page.addTargetRequests(listUrls);//把所有帖子页的URL加入抓取队列
        listUrls.forEach(e -> page.addTargetRequests(URLGeneratedUtil.generatePostURLs(e)));
        String url = page.getUrl().toString();
        page.addTargetRequest(url.substring(0,URL_LENGTH).concat((Integer.parseInt(url.substring(URL_LENGTH)) + 1) + ""));//把所有列表页加入抓取队列
*/    }

    /**
     * 抓取主题帖内信息
     * @param page 当前页面对象
     */
    private void crawlPost(Page page) {
        page.addTargetRequests(page.getHtml().links().regex(URL_USER).all());//把所有用户主页的URL加入抓取队列
        //帖子标题
        String title = page.getHtml().xpath("//div[@class='bbs-hd-h1']/h1[@id='j_data']/text()").toString();
        //帖子作者
        String postAuthor = page.getHtml().xpath("//div[@class='author']//a[@class='u'][1]/text()").toString();
        //回复数
        //int replyNum = Integer.parseInt(page.getHtml().xpath("//div[@class='bbs-hd-h1']/h1[@id='j_data']/@data-replies").toString());
        int replyNum = Integer.parseInt(page.getHtml().xpath("//div[@class='bbs-hd-h1']/span[1]/span[1]/text()").toString().replaceAll("[\\u4e00-\\u9fa5]+", ""));

        setPost(title, postAuthor, replyNum, page);
        setTitleWord(title, page);

        //评论内容
        List<String> contentList = page.getHtml().xpath("//div[@class='w_reply clearfix']//td/text()").all();
        //评论点亮数
        List<String> litNumList = page.getHtml().xpath("//div[@class='w_reply clearfix']//span[@class='ilike_icon_list']/span[@class='stime']/text()").all();
        //评论作者
        List<String> commentAuthors = page.getHtml().xpath("//div[@class='w_reply clearfix']//div[@class='author']//a[@class='u']/text()").all();

        //for(int i = 0; i < contentList.size(); i++) {
        setComment(title,contentList, litNumList, commentAuthors, page);
        //}
    }

    /**
     * 抓取各用户主页
     * @param page 当前页面对象
     */
    private void crawlUser(Page page) {
        //用户名
        String name = page.getHtml().xpath("//div[@itemprop='name']/text()").toString();
        //用户所在地
        String address = page.getHtml().xpath("//div[@class='personalinfo']//span[@itemprop='address']/text()").toString();
        //用户主队
        String homeTeam = page.getHtml().xpath("//div[@class='personalinfo']//span[@itemprop='affiliation'][1]/a/text()").toString();
        //用户性别
        String gender = page.getHtml().xpath("//div[@class='personalinfo']//span[@itemprop='gender']/text()").toString();
        //用户被访问量
        int views = Integer.parseInt(page.getHtml().xpath("//div[@class='personal']//span[@class='f666'][1]/text()").toString().replaceAll("[^0-9]", ""));

        setUser(name, address, homeTeam, gender, views, page);
    }

    /**
     * 将抓取到的主题帖post信息传给pipeline进行后续处理
     * @param title 帖子标题
     * @param author 帖子作者
     * @param replyNum 帖子回复数
     * @param page 当前页面对象
     */
    private void setPost(String title, String author, int replyNum, Page page) {
       /* Post post = new Post();
        post.setTitle(title);
        post.setAuthor(author);
        post.setReplyNum(replyNum);
        page.putField("postInfo", post);*/
    }

    /**
     * 将主题帖下的热评comment传给pipeline进行后续处理
     * @param title 热评所在主题帖的标题
     * @param contentList 热评列表
     * @param litNumList 评论点亮数列表
     * @param commentAuthors 评论作者列表
     * @param page 当前页面对象
     */
    private void setComment(String title, List<String> contentList, List<String> litNumList, List<String> commentAuthors, Page page) {//存放帖子下的热评信息
        /*CommentList commentList = new CommentList();
        commentList.setTitle(title);
        commentList.setContentList(contentList);
        commentList.setLitNumList(litNumList);
        commentList.setCommentAuthors(commentAuthors);
        page.putField("commentInfo", commentList);*/
    }

    /**
     * 将主题帖标题的分词信息传给pipeline进行后续处理
     * @param title 主题帖标题
     * @param page 当前页面对象
     */
    private void setTitleWord(String title, Page page) {//存放抓取的帖子标题的分词
        title = title.replaceAll( "[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]" , "");
        String[] strings = ToAnalysis.parse(title).toStringWithOutNature().split(",");//分词的结果是用","分隔的
        //for(String word: strings) {
          //  TitleWord titleWord = new TitleWord();
            //titleWord.setWord(word);
            page.putField("titleWordInfo", strings);
        //}
    }

    /**
     * 将抓取到的用户信息user传给pipeline进行后续处理
     * @param name 用户名
     * @param address 所在地
     * @param homeTeam NBA主队
     * @param gender 性别
     * @param views 用户主页访问量
     * @param page 当前页面对象
     */
    private void setUser(String name, String address, String homeTeam, String gender, int views, Page page) {
        /*User user = new User();
        user.setName(name);
        user.setAddress(address);
        user.setHomeTeam(homeTeam);
        user.setGender(gender);
        user.setViews(views);
        page.putField("userInfo", user);*/
    }
}
