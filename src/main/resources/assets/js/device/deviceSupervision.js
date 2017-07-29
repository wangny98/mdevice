
ineuronApp.controller('DeviceMonitorController', ['$http', '$scope', '$stateParams', '$rootScope', '$uibModal', '$location', '$cookies', '$state', 'DTOptionsBuilder', 'DTColumnDefBuilder',
	function($http, $scope, $stateParams, $rootScope, $uibModal, $location, $cookies, $state, DTOptionsBuilder, DTColumnDefBuilder) {
	var vm = this;
	// var selectedProductStr = $stateParams.productStr;
	// var selectedProduct = eval('(' + selectedProductStr + ')');
	
	$http({
		url : '/device/deviceattributelist',
		method : 'GET'
	}).success(function(data) {
		updateApiToken(data, $cookies);
		vm.deviceAttributes = data.value;
	}).error(function(data, status) {
		handleError(status, $rootScope, $uibModal);
		//ineuronApp.confirm("提示","获取属性列表失败！", 'sm', $rootScope, $uibModal);
		console.log("error");
	});

	vm.dtOptions = DTOptionsBuilder.newOptions().withPaginationType(
			'full_numbers');
	vm.dtColumnDefs = [ DTColumnDefBuilder.newColumnDef(0),
			DTColumnDefBuilder.newColumnDef(1).notSortable() ];
	
	vm.updateDeviceAttribute=updateDeviceAttribute;
	function updateDeviceAttribute(index){
		$state.go("createAndUpdateDeviceAttribute", {deviceAttributeStr: JSON.stringify(vm.deviceAttributes[index])});
	}
	
	vm.createDeviceAttribute=createDeviceAttribute;
	function createDeviceAttribute(){
		// alert("index: "+index);
		$state.go("createAndUpdateDeviceAttribute", {deviceAttributeStr: JSON.stringify(null)});
	}
	
	vm.deleteDeviceAttribute=deleteDeviceAttribute;
	function deleteDeviceAttribute(index){
		ineuronApp.confirm("确认","确定删除吗？", 'sm', $rootScope, $uibModal).result.then(function(clickok){  
			if(clickok){
				 $http({
					url : '/device/deletedeviceattribute',
					method : 'POST',
					data : {
						name : vm.deviceAttributes[index].name
					}
				}).success(function(data) {
					ineuronApp.confirm("提示","删除成功！", 'sm', $rootScope, $uibModal);
					updateApiToken(data, $cookies);
					$state.go("deviceAttributeList", null, {reload:true});
				}).error(function(data,status) {
					handleError(status, $rootScope, $uibModal);
					//ineuronApp.confirm("提示","删除失败！", 'sm', $rootScope, $uibModal);
					console.log("error");
				})
			}
		});		
	}
		
}]);


ineuronApp.controller('DeviceDataAnalysisController', ['$scope', '$stateParams', '$http', '$state', '$cookies', '$rootScope', '$uibModal',
  function($scope, $stateParams, $http, $state, $cookies, $rootScope, $uibModal) {
	var deviceAttribute = eval('(' + $stateParams.deviceAttributeStr + ')');
	
	$scope.showReport=function(){
		$scope.options = {
				chart: {
					type: 'lineChart',
					height: 450,
					width:1000,
					margin : {
						top: 20,
						right: 20,
						bottom: 40,
						left: 55
					},
					x: function(d){ return d[0]; },
					y: function(d){ return d[1]; },
					useInteractiveGuideline: true,
					color: d3.scale.category10().range(),

					xAxis: {
						axisLabel: '时间'
					},
					yAxis: {
						axisLabel: '状态值  ',
						tickFormat: function(d){
							return d3.format('.02f')(d);
						},
						axisLabelDistance: -10
					},
					callback: function(chart){
						console.log("!!! lineChart callback !!!");
					}
				},
				title: {
					enable: true,
					text: '设备历史数据分析'
				},
				subtitle: {
					enable: true,
					text: '',
					css: {
						'text-align': 'center',
						'margin': '10px 13px 0px 7px'
					}
				},
				caption: {
					/*
					 * enable: true, html: '<b>Figure 1.</b> xxx, <span
					 * style="text-decoration: underline;">xx</span> <i>xxx</i> ',
					 * css: { 'text-align': 'justify', 'margin': '10px 13px 0px
					 * 7px' }
					 */
				}
		};

		$scope.data = [
		               {
		                   key: "罐区新油罐出料气动隔膜泵气阀",
		                   values: [ [ 1 , 3] ,[ 2 , 5] ]
		               },
		               {
		                   key: "南边A线新油罐出料气动隔膜泵气阀",
		                   values: [ [ 1 , 5],[ 2 , 8] ]
		               },
		               {
		                   key: "罐区使用油罐出料气动隔膜泵气阀",
		                   values: [ [ 1 , 8],[ 2 , 9] ]
		               }
		           ];
	}
	
}]);

