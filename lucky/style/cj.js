var xinm = [
    "小米音响1个",
    "小米音响1个",
    "小米音响1个",
    "小米音响1个",
    "小米音响1个",
    "榨汁机1个",
    "榨汁机1个",
    "榨汁机1个",
    "榨汁机1个",
    "榨汁机1个",
    "榨汁机1个",
    "网红音响1个",
    "网红音响1个",
    "网红音响1个",
    "复古音响1个",
    "复古音响1个",
    "复古音响1个"
];

var nametxt = $('.name');
var pcount = xinm.length-1;//参加人数
var runing = true;
var td = xinm.length;//内定中奖,从最小奖开始，共10个名额
var num = 0;
var t;
var paiming = 0;
//开始停止
function start() {
	if (runing) {
		runing = false;
		$('#btntxt').removeClass('start').addClass('stop');
		$('#btntxt').html('停止');
		startNum()
	} else {
		runing = true;
		$('#btntxt').removeClass('stop').addClass('start');
		$('#btntxt').html('开始');
		stop();
		zd();//内定中奖
	}
}
//循环参加名单
function startNum() {
	num = Math.floor(Math.random() * pcount);
	nametxt.html(xinm[num]);
	//phonetxt.html(phone[num]);
	t = setTimeout(startNum, 0);
}
//停止跳动
function stop() {
	pcount = xinm.length-1;
	clearInterval(t);
	t = 0;
}
//从一等奖开始指定前3名
function zd() {
	paiming++;
//	if(td <= 3){   
//    if (td == 1) {
//        nametxt.html('周一一');
//        phonetxt.html('15112345678');
//        $('.list').prepend("<p>"+td+' '+"周一一 -- 15112345678</p>");
//    }
//		if (td == 2) {
//        nametxt.html('李二二');
//        phonetxt.html('151000000000');
//        $('.list').prepend("<p>"+td+' '+"李二二 -- 151000000000</p>");
//    }
//		if (td == 3) {
//        nametxt.html('张三三');
//        phonetxt.html('1511111111');
//        $('.list').prepend("<p>"+td+' '+"张三三 -- 1511111111</p>");
//    }
//	}else if(td > 0){
		//if(td > 0){
        if(xinm.length <= 0){
            alert("抽奖结束");
            runing=true;
            return;
        }
		//打印中奖者名单
		//$('.list').prepend("<p style=\"text-align: center\">"+(paiming)+' '+xinm[num]+ "</p>");
        $('.list').append("<p>"+(paiming)+' '+xinm[num]+ "</p>");
		//将已中奖者从数组中"删除",防止二次中奖
		xinm.splice($.inArray(xinm[num], xinm), 1);
		//}
		td = td - 1;
}
