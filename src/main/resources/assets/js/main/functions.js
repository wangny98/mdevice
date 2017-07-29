var ineuronFuncs = angular.module('ineuron.funcs', []);

//用户管理
ineuronFuncs.userAdmin = {id:"1", name:"用户管理", ops:[ 
		{id : "1", operationname : "查询", ticked : false}, 
		{id : "2", operationname : "新增", ticked : false}, 
		{id : "3", operationname : "编辑", ticked : false}, 
		{id : "4", operationname : "删除", ticked : false} 
	],
	output: {}
}

//角色管理
ineuronFuncs.roleAdmin = {id:"2", name:"角色管理", ops:[ 
		{id : "1", operationname : "查询", ticked : false}, 
		{id : "2", operationname : "新增", ticked : false}, 
		{id : "3", operationname : "编辑", ticked : false}, 
		{id : "4", operationname : "删除", ticked : false}
	],
	output: {}
}

//设备管理
ineuronFuncs.deviceAdmin = {id:"11", name:"设备管理", ops:[ 
		{id : "1", operationname : "查询", ticked : false}, 
		{id : "2", operationname : "新增", ticked : false}, 
		{id : "3", operationname : "编辑", ticked : false}, 
		{id : "4", operationname : "删除", ticked : false}
	],
	output: {}
}

//设备属性管理
ineuronFuncs.deviceAttrAdmin = {id:"12", name:"设备属性管理", ops:[ 
		{id : "1", operationname : "查询", ticked : false}, 
		{id : "2", operationname : "新增", ticked : false}, 
		{id : "3", operationname : "编辑", ticked : false}, 
		{id : "4", operationname : "删除", ticked : false} 
	],
	output: {}
}



//设备监控
ineuronFuncs.deviceMonitorAdmin = {id:"21", name:"设备监控", ops:[ 
		{id : "1", operationname : "查询", ticked : false}, 
		{id : "2", operationname : "新增", ticked : false}, 
		{id : "3", operationname : "编辑", ticked : false}, 
		{id : "4", operationname : "删除", ticked : false} 
	],
	output: {}
}

//设备历史数据分析
ineuronFuncs.dataAnalysisAdmin = {id:"22", name:"历史数据分析", ops:[ 
		{id : "1", operationname : "查询", ticked : false}, 
		{id : "2", operationname : "新增", ticked : false}, 
		{id : "3", operationname : "编辑", ticked : false}, 
		{id : "4", operationname : "删除", ticked : false} 
	],
	output: {}
}

ineuronFuncs.funcList = [ineuronFuncs.userAdmin, 
	ineuronFuncs.roleAdmin, 
	ineuronFuncs.deviceAdmin,
	ineuronFuncs.deviceAttrAdmin,
	ineuronFuncs.deviceMonitorAdmin,
	ineuronFuncs.dataAnalysisAdmin
];

ineuronFuncs.getFuncs = function(){
	ineuronFuncs.resetFuncs();
	return ineuronFuncs.funcList;
}

ineuronFuncs.resetFuncs = function(){
	for(var i in ineuronFuncs.funcList) {
		var func = ineuronFuncs.funcList[i];
		for(var j in func.ops){
			func.ops[j].ticked = false;
		}	
			
	}
}

ineuronFuncs.getFunc = function(id){
	for(var i in ineuronFuncs.funcList) {
		if(ineuronFuncs.funcList[i].id == id){
			return ineuronFuncs.funcList[i];
		}
	}
	return null;
}
