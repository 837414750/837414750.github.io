var g_Interval = 1;
var g_PersonCount = 500;//参加抽奖人数
var g_Timer;
var running = false;
function beginRndNum(trigger){
	if(running){
		running = false;
		clearTimeout(g_Timer);		
		$(trigger).val("开始");
		$('#ResultNum').css('color','red');
	}
	else{
		running = true;
		$('#ResultNum').css('color','black');
		$(trigger).val("停止");
		beginTimer();
	}
}

function updateRndNum(){
	//var num = Math.floor(Math.random()*g_PersonCount+1);
	//此处只需要把你需要用到的圣经章节写到数组里面即可,每个用逗号隔开
	var arr = ['太7：1-3（红）','太7：1-3（黄）','太7：1-3（蓝）','提后3：16（红）','提后3：16（黄）','提后3：16（蓝）',			'来4：12（红）','来4：12（黄）','来4：12（蓝）','诗19：9（红）','诗19：9（黄）','诗19：9（蓝）',
			  '西3：16（红）','西3：16（黄）','西3：16（蓝）','传12：13-14（红）','传12：13-14（黄）','传12：13-14（蓝）','箴4：5-8（红）','箴4：5-8（黄）','箴4：5-8（蓝）','约3：16（红）','约3：16（黄）','约3：16（蓝）',
			  '诗37：31（红）','诗37：31（黄）','诗37：31（蓝）','诗119：35（红）','诗119：35（黄）','诗119：35（蓝）','约15:7-11（红）','约15:7-11（黄）','约15:7-11（蓝）','创1:1-3（红）','创1:1-3（黄）','创1:1-3（蓝）','出3:2-3（红）','出3:2-3（黄）','出3:2-3（蓝）','民5:6-8（红）','民5:6-8（黄）','民5:6-8（蓝）','太1：1-3（红）','太1：1-3（黄）','太1：1-3（蓝）'];
	var index = Math.floor((Math.random()*arr.length)); 
	$('#ResultNum').html(arr[index]);
}

function beginTimer(){
	g_Timer = setTimeout(beat, g_Interval);
}

function beat() {
	g_Timer = setTimeout(beat, g_Interval);
	updateRndNum();
}