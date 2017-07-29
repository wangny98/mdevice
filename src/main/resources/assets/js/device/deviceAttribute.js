
ineuronApp.controller('DeviceAttributeListController', ['$http', '$scope', '$stateParams', '$rootScope', '$uibModal', '$location', '$cookies', '$state', 'DTOptionsBuilder', 'DTColumnDefBuilder',
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


ineuronApp.controller('DeviceAttributeCreateAndUpdateController', ['$scope', '$stateParams', '$http', '$state', '$cookies', '$rootScope', '$uibModal',
  function($scope, $stateParams, $http, $state, $cookies, $rootScope, $uibModal) {
	var deviceAttribute = eval('(' + $stateParams.deviceAttributeStr + ')');
	
	var forCreate=true;
	if (deviceAttribute!=null){
		forCreate=false;	
		$scope.deviceAttributeName=deviceAttribute.name;
		$scope.deviceAttributeCode=deviceAttribute.code;
		$scope.deviceAttributeDescription=deviceAttribute.description;
	} 
	
	var vm = this;	
	$scope.existedDeviceAttributeName=false;
	$scope.existedDeviceAttributeCode=false;
	
	
	$http({
		url : '/device/deviceattributecategorylist',
		method : 'GET'
	}).success(function(data) {
		vm.deviceAttributeCategories = data.value;
		if(!forCreate){
		for (var i in vm.deviceAttributeCategories){
			if(vm.deviceAttributeCategories[i].id==deviceAttribute.deviceAttributeCategoryId){
				vm.deviceAttributeCategories[i].ticked=true;
				break;
			}
		}
		}
		updateApiToken(data, $cookies);
	}).error(function(data, status) {
		handleError(status, $rootScope, $uibModal);
		//ineuronApp.confirm("提示","调用属性类型列表失败！", 'sm', $rootScope, $uibModal);
		console.log("error to get device attribute category list ");
	});				

	
	$scope.CheckDeviceAttributeName=function(){
		$http({
			url : '/device/getdeviceattributebyname',
			method : 'POST',
			data :  $scope.deviceAttributeName
		}).success(function(data) {
			// updateApiToken(data, $cookies);
			var a = data.value;
			if(forCreate){
				if (a==null) $scope.existedDeviceAttributeName=false;
				else $scope.existedDeviceAttributeName=true;
			}
			else 
			{
				if($scope.deviceAttributeName==deviceAttribute.name) $scope.existedDeviceAttributeName=false;
				else {
					if(a==null) $scope.existedDeviceAttributeName=false; 
					else $scope.existedDeviceAttributeName=true;
			}
			}
		}).error(function(data, status) {
			handleError(status, $rootScope, $uibModal);
			//ineuronApp.confirm("提示","依据名称调用属性失败！", 'sm', $rootScope, $uibModal);
			console.log("error to get device attribute ");
		});				
	}

	
	$scope.CheckDeviceAttributeCode=function(){
		$http({
			url : '/device/getdeviceattributebycode',
			method : 'POST',
			data :  $scope.deviceAttributeCode
		}).success(function(data) {
			// updateApiToken(data, $cookies);
			var a = data.value;
			if(forCreate){
				if (a==null) $scope.existedDeviceAttributeCode=false;
				else $scope.existedDeviceAttributeCode=true;
			}
			else 
			{
				if($scope.deviceAttributeCode==deviceAttribute.code) $scope.existedDeviceAttributeCode=false;
				else {
					if(a==null) $scope.existedDeviceAttributeCode=false; 
					else $scope.existedDeviceAttributeCode=true;
			}
			}
		}).error(function(data, status) {
			handleError(status, $rootScope, $uibModal);
			//ineuronApp.confirm("提示","依据名称调用属性失败！", 'sm', $rootScope, $uibModal);
			console.log("error to get device attribute ");
		});				
	}
	
	

	vm.createAndUpdateDeviceAttribute = createAndUpdateDeviceAttribute;
	function createAndUpdateDeviceAttribute() {
    //alert("create device attribute");
		if(forCreate){
			//alert("create"+$scope.deviceAttributeName+"  "+$scope.selectedDeviceAttributeCategory[0].id);
			$http({
				url : '/device/createdeviceattribute',
				method : 'POST',
				data : {
					name : $scope.deviceAttributeName,
					code: $scope.deviceAttributeCode,
					deviceAttributeCategoryId: $scope.selectedDeviceAttributeCategory[0].id,
					description : $scope.deviceAttributeDescription
				}
			}).success(function(data) {
				updateApiToken(data, $cookies);
				ineuronApp.confirm("提示","属性添加成功！", 'sm', $rootScope, $uibModal);		
				$state.go("deviceAttributeList");
			}).error(function(data,status) {
				//handleError(status, $rootScope, $uibModal);
				ineuronApp.confirm("提示","添加属性失败！", 'sm', $rootScope, $uibModal);
				console.log("error");
		  		})
		}
		else{
		 $http({
			url : '/device/updatedeviceattribute',
			method : 'POST',
			data : {
				id : deviceAttribute.id,
				name : $scope.deviceAttributeName,
				code: $scope.deviceAttributeCode,
				description : $scope.deviceAttributeDescription,
				deviceAttributeCategoryId: $scope.selectedDeviceAttributeCategory[0].id
			}
		}).success(function(data) {
			updateApiToken(data, $cookies);
			ineuronApp.confirm("提示","属性修改成功！", 'sm', $rootScope, $uibModal);		
			$state.go("deviceAttributeList");
		}).error(function(data, status) {
			handleError(status, $rootScope, $uibModal);
			//ineuronApp.confirm("提示","修改失败！", 'sm', $rootScope, $uibModal);
			console.log("error");
		})
	  }
	}
	
	
	vm.backward = backward;
	function backward() {
		$state.go("deviceAttributeList");
	}
	
}]);

