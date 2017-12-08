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
	var arr = ['太7：1-3','提后3：16','来4：12','诗19：9','西3：16','传12：13-14','箴4：5-8',
				'约3：16','诗37：31','诗119：35','约15:7-11','创1:1-3','出3:2-3','民5:6-8'];
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