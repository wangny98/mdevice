
ineuronApp.controller('DeviceListController', ['$http', '$scope', '$stateParams', '$rootScope', '$uibModal', '$location', '$cookies', '$state', 'DTOptionsBuilder', 'DTColumnDefBuilder',
	function($http, $scope, $stateParams, $rootScope, $uibModal, $location, $cookies, $state, DTOptionsBuilder, DTColumnDefBuilder) {
	var vm = this;
	
	$http({
		url : '/device/devicelist',
		method : 'GET'
	}).success(function(data) {
		updateApiToken(data, $cookies);
		vm.devices = data.value;
	}).error(function(data, status) {
		handleError(status, $rootScope, $uibModal);
		//ineuronApp.confirm("提示","获取属性列表失败！", 'sm', $rootScope, $uibModal);
		console.log("error");
	});
	
	/*$http({
		url : '/device/getdevicesbyattribute',
		method : 'POST',
		data : "LPA"
	}).success(function(data) {
		updateApiToken(data, $cookies);
		vm.devices = data.value;
	}).error(function(data, status) {
		handleError(status, $rootScope, $uibModal);
		//ineuronApp.confirm("提示","获取属性列表失败！", 'sm', $rootScope, $uibModal);
		console.log("error");
	});
*/
	vm.dtOptions = DTOptionsBuilder.newOptions().withPaginationType(
			'full_numbers');
	vm.dtColumnDefs = [ DTColumnDefBuilder.newColumnDef(0),
			DTColumnDefBuilder.newColumnDef(1).notSortable() ];
	
	vm.updateDevice=updateDevice;
	function updateDevice(index){
		$state.go("createAndUpdateDevice", {deviceStr: JSON.stringify(vm.devices[index])});
	}
	
	vm.createDevice=createDevice;
	function createDevice(){
		// alert("index: "+index);
		$state.go("createAndUpdateDevice", {deviceStr: JSON.stringify(null)});
	}
	
	vm.deleteDevice=deleteDevice;
	function deleteDevice(index){
		ineuronApp.confirm("确认","确定删除吗？", 'sm', $rootScope, $uibModal).result.then(function(clickok){  
			if(clickok){
				 $http({
					url : '/device/deletedevice',
					method : 'POST',
					data : {
						name : vm.devices[index].name
					}
				}).success(function(data) {
					ineuronApp.confirm("提示","删除成功！", 'sm', $rootScope, $uibModal);
					updateApiToken(data, $cookies);
					$state.go("deviceList", null, {reload:true});
				}).error(function(data,status) {
					handleError(status, $rootScope, $uibModal);
					//ineuronApp.confirm("提示","删除失败！", 'sm', $rootScope, $uibModal);
					console.log("error");
				})
			}
		});		
	}
		
}]);


ineuronApp.controller('DeviceCreateAndUpdateController', ['$scope', '$stateParams', '$http', '$state', '$cookies', '$rootScope', '$uibModal',
  function($scope, $stateParams, $http, $state, $cookies, $rootScope, $uibModal) {
	var device = eval('(' + $stateParams.deviceStr + ')');
	
	var vm = this;	
	$scope.existedDeviceName=false;
	$scope.existedDeviceCode=false;
	
	vm.dataSource = [
	                      	{	name: "PLC"},
	                      	{	name: "人工输入"}
	                     ];
	
	var forCreate=true;
	if (device!=null){
		forCreate=false;	
		$scope.deviceName=device.name;
		$scope.deviceCode=device.code;
		$scope.deviceDescription=device.description;
		$scope.plcAddress=device.plcAddress;
		$scope.plcDataFrequency=device.plcDataFrequency;
		
		var deviceCodeArray=$scope.deviceCode.split("-");	
		
		for (var i in vm.dataSource){
				if(vm.dataSource[i].name==device.dataSource){
					vm.dataSource[i].ticked=true;
			    break;
		    }
	   }		
	} 
	
    //get device attributes by 3 categories 
	
	$http({
		url : '/device/deviceattributesbycategoryid',
		method : 'POST',
		data :  1
	}).success(function(data) {
		vm.deviceAttributesLocations = data.value;
		if(!forCreate){
		for (var i in vm.deviceAttributesLocations){
				for(var j in deviceCodeArray){
					if(vm.deviceAttributesLocations[i].code==deviceCodeArray[j]){
						vm.deviceAttributesLocations[i].ticked=true;
				    break;
			    }
			}
		 }
		}
		updateApiToken(data, $cookies);
	}).error(function(data, status) {
		handleError(status, $rootScope, $uibModal);
		console.log("error to get device attribute by category id ");
	});		
	
	$http({
		url : '/device/deviceattributesbycategoryid',
		method : 'POST',
		data :  2
	}).success(function(data) {
		vm.deviceAttributesDevices = data.value;
		if(!forCreate){
			for (var i in vm.deviceAttributesDevices){
					for(var j in deviceCodeArray){
						if(vm.deviceAttributesDevices[i].code==deviceCodeArray[j]){
							vm.deviceAttributesDevices[i].ticked=true;
					    break;
				    }
				}
			}
		}
		updateApiToken(data, $cookies);
	}).error(function(data, status) {
		handleError(status, $rootScope, $uibModal);
		console.log("error to get device attribute by category id ");
	});	
	
	$http({
		url : '/device/deviceattributesbycategoryid',
		method : 'POST',
		data :  3
	}).success(function(data) {
		vm.deviceAttributesControllers = data.value;
		if(!forCreate){
			for (var i in vm.deviceAttributesControllers){
					for(var j in deviceCodeArray){
						if(vm.deviceAttributesControllers[i].code==deviceCodeArray[j]){
							vm.deviceAttributesControllers[i].ticked=true;
					    break;
				    }
				}
			}
		}
		updateApiToken(data, $cookies);
	}).error(function(data, status) {
		handleError(status, $rootScope, $uibModal);
		console.log("error to get device attribute by category id ");
	});	
	
	
	$scope.CheckDeviceNameAndCode=function(){
		
		$scope.deviceName=$scope.selectedDeviceAttributeLocation[0].name+
		  				  $scope.selectedDeviceAttributeDevice[0].name+
		  				  $scope.selectedDeviceAttributeController[0].name;
		
		$scope.deviceCode=$scope.selectedDeviceAttributeLocation[0].code+"-"+
						  $scope.selectedDeviceAttributeDevice[0].code+"-"+
						  $scope.selectedDeviceAttributeController[0].code;
		
		$http({
			url : '/device/getdevicebyname',
			method : 'POST',
			data :  $scope.deviceName
		}).success(function(data) {
			// updateApiToken(data, $cookies);
			var a = data.value;
			if(forCreate){
				if (a==null) $scope.existedDeviceName=false;
				else $scope.existedDeviceName=true;
			}
			else 
			{
				if($scope.deviceName==device.name) $scope.existedDeviceName=false;
				else {
					if(a==null) $scope.existedDeviceName=false; 
					else $scope.existedDeviceName=true;
			}
			}
		}).error(function(data, status) {
			handleError(status, $rootScope, $uibModal);
			console.log("error to get device name ");
		});		
		
		$http({
			url : '/device/getdevicebycode',
			method : 'POST',
			data :  $scope.deviceCode
		}).success(function(data) {
			// updateApiToken(data, $cookies);
			var a = data.value;
			if(forCreate){
				if (a==null) $scope.existedDeviceCode=false;
				else $scope.existedDeviceCode=true;
			}
			else 
			{
				if($scope.deviceCode==device.code) $scope.existedDeviceCode=false;
				else {
					if(a==null) $scope.existedDeviceCode=false; 
					else $scope.existedDeviceCode=true;
			}
			}
		}).error(function(data, status) {
			handleError(status, $rootScope, $uibModal);
			console.log("error to get device code ");
		});				
	}
	
	
	$http({
		url : '/device/devicetypelist',
		method : 'GET'
	}).success(function(data) {
		vm.deviceTypes = data.value;
		if(!forCreate){
		for (var i in vm.deviceTypes){
			if(vm.deviceTypes[i].id==device.typeId){
				vm.deviceTypes[i].ticked=true;
				break;
			}
		}
		}
		updateApiToken(data, $cookies);
	}).error(function(data, status) {
		handleError(status, $rootScope, $uibModal);
		//ineuronApp.confirm("提示","调用属性类型列表失败！", 'sm', $rootScope, $uibModal);
		console.log("error to get device type list ");
	});				
	

	vm.createAndUpdateDevice = createAndUpdateDevice;
	function createAndUpdateDevice() {
    //alert("create device attribute");
		if(forCreate){
			//alert("create"+$scope.selectedDataSource[0].name+"  "+$scope.PLCAddress+ " "+$scope.PLCDataFrequency);
			$http({
				url : '/device/createdevice',
				method : 'POST',
				data : {
					name : $scope.deviceName,
					code: $scope.deviceCode,
					typeId: $scope.selectedDeviceType[0].id,
					dataSource: $scope.selectedDataSource[0].name,
					plcAddress: $scope.plcAddress,
					plcDataFrequency: $scope.plcDataFrequency,
					description : $scope.deviceDescription
				}
			}).success(function(data) {
				updateApiToken(data, $cookies);
				ineuronApp.confirm("提示","设备添加成功！", 'sm', $rootScope, $uibModal);		
				$state.go("deviceList");
			}).error(function(data,status) {
				//handleError(status, $rootScope, $uibModal);
				ineuronApp.confirm("提示","设备添加失败！", 'sm', $rootScope, $uibModal);
				console.log("error");
		  		})
		}
		else{
		 $http({
			url : '/device/updatedevice',
			method : 'POST',
			data : {
				id : device.id,
				name : $scope.deviceName,
				code: $scope.deviceCode,
				typeId: $scope.selectedDeviceType[0].id,
				dataSource: $scope.selectedDataSource[0].name,
				plcAddress: $scope.plcAddress,
				plcDataFrequency: $scope.plcDataFrequency,
				description : $scope.deviceDescription
			}
		}).success(function(data) {
			updateApiToken(data, $cookies);
			ineuronApp.confirm("提示","设备修改成功！", 'sm', $rootScope, $uibModal);		
			$state.go("deviceList");
		}).error(function(data, status) {
			handleError(status, $rootScope, $uibModal);
			//ineuronApp.confirm("提示","修改失败！", 'sm', $rootScope, $uibModal);
			console.log("error");
		})
	  }
	}
	
	
	vm.backward = backward;
	function backward() {
		$state.go("deviceList");
	}
	
}]);

