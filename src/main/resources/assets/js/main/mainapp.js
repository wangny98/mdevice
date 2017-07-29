//define the package as ineuronApp
var ineuronApp = angular.module('ineuronApp', [ 'ui.router', 'ngCookies', 'datatables', 'isteven-multi-select',
                                                'ui.bootstrap','ui.sortable','ngFileUpload','nvd3','tm.pagination', 'ngRadialGauge']);

ineuronApp.config(function($stateProvider) {


	var aboutState = {
		name : 'frontpage',
		url : '/frontpage',
		templateUrl : '/ineuron/frontpage.html'
	}
	
	
	//User Managerment
	
	var userManagementState = {
		name : 'userManagement',
		url : 'userManagement',
		templateUrl : '/ineuron/user/list.html'
	}
	
	var roleManagementState = {
			name : 'roleManagement',
			url : 'roleManagement',	
			templateUrl : '/ineuron/user/rolelist.html'
		}

	var updateUserState = {
		name : 'updateUser',
		url : 'updateUser/:userStr',
		templateUrl : '/ineuron/user/updateUser.html'
		}
	
	var updateRoleState = {
			name : 'updateRole',
			url : 'updateRole/:roleStr',
			templateUrl : '/ineuron/user/updateRole.html'
		}
	
	var createRoleState = {
			name : 'createRole',
			url : 'createRole',
			templateUrl : '/ineuron/user/createRole.html'
		}
	
	var createUserState = {
			name : 'createUser',
			url : 'createUser',
			templateUrl : '/ineuron/user/createUser.html'
		}

	
	//Device Management
	
	var createAndUpdateDeviceAttributeState = {
			name : 'createAndUpdateDeviceAttribute',
			url : 'createAndUpdateDeviceAttribute/:deviceAttributeStr',
			templateUrl : '/ineuron/device/createAndUpdateDeviceAttribute.html'
		}
	
	var deviceAttributeManagementState = {
			name : 'deviceAttributeList',
			url : 'deviceAttributeList',
			templateUrl : '/ineuron/device/deviceAttributeList.html'
		}
	
	var deviceManagementState = {
			name : 'deviceList',
			url : 'deviceList/:deviceStr',
			templateUrl : '/ineuron/device/deviceList.html'
		}
	
	var createAndUpdateDeviceState = {
			name : 'createAndUpdateDevice',
			url : 'createAndUpdateDevice/:deviceStr',
			templateUrl : '/ineuron/device/createAndUpdateDevice.html'
		}
	
	var deviceMonitorState = {
			name : 'deviceMonitor',
			url : 'deviceMonitor',
			templateUrl : '/ineuron/device/deviceMonitor.html'
		}
	
	var deviceDataAnalysisState = {
			name : 'deviceDataAnalysis',
			url : 'deviceDataAnalysis',
			templateUrl : '/ineuron/device/deviceDataAnalysis.html'
		}
	
	
	//Product Management
	
	var createProductCategoryState = {
			name : 'createProductCategory',
			url : 'createProductCategory',
			templateUrl : '/ineuron/product/createProductCategory.html'
		}
	
	var productCategoryManagementState = {
			name : 'productCategoryList',
			url : 'productCategoryList',
			templateUrl : '/ineuron/product/productCategoryList.html'
		}
	
	var updateProductCategoryState = {
			name : 'updateProductCategory',
			url : 'updateProductCategory/:productCategoryStr',
			templateUrl : '/ineuron/product/updateProductCategory.html'
		}
	
	var productManagementState = {
			name : 'productList',
			url : 'productList/:productCategoryStr',
			templateUrl : '/ineuron/product/productList.html'
		}
	
	var allProductManagementState = {
			name : 'allProductList',
			url : 'allProductList',
			templateUrl : '/ineuron/product/allProductList.html'
		}
	
	var createProductState = {
			name : 'createProduct',
			url : 'createProduct',
			templateUrl : '/ineuron/product/createProduct.html'
		}
	
	var updateProductState = {
			name : 'updateProduct',
			url : 'updateProduct/:productStr',
			templateUrl : '/ineuron/product/updateProduct.html'
		}
	
	var updateProductPriceState = {
			name : 'updateProductPrice',
			url : 'updateProductPrice/:productStr',
			templateUrl : '/ineuron/product/updateProductPrice.html'
		}
	
	
	var attributeManagementState = {
			name : 'attributeList',
			url : 'attributeList',
			templateUrl : '/ineuron/product/attributeList.html'
		}
	
	var createAndUpdateAttributeState = {
			name : 'createAndUpdateAttribute',
			url : 'createAndUpdateAttribute/:attributeStr',
			templateUrl : '/ineuron/product/createAndUpdateAttribute.html'
		}
	
	var productManufacturingProcessState = {
			name : 'productManufacturingProcess',
			url : 'productManufacturingProcess/:productStr',
			templateUrl : '/ineuron/product/manufacturingprocess.html'
		}
	
	var formulaManagementState = {
			name : 'formulaList',
			url : 'formulaList',
			templateUrl : '/ineuron/product/formulaList.html'
		}
	
	var updateFormulaState = {
			name : 'updateFormula',
			url : 'updateFormula/:formulaStr',
			templateUrl : '/ineuron/product/updateFormula.html'
		}
	
	var createFormulaState = {
			name : 'createFormula',
			url : 'createFormula/',
			templateUrl : '/ineuron/product/createFormula.html'
		}

	var productPackageTypeManagementState = {
			name : 'productPackageTypeList',
			url : 'productPackageTypeList',
			templateUrl : '/ineuron/product/productPackageTypeList.html'
		}
	
	var createAndUpdateProductPackageTypeState = {
			name : 'createAndUpdateProductPackageType',
			url : 'createAndUpdateProductPackageType/:productPackageTypeStr',
			templateUrl : '/ineuron/product/createAndUpdateProductPackageType.html'
		}
	

	
	$stateProvider.state(userManagementState);
	$stateProvider.state(roleManagementState);
	$stateProvider.state(updateUserState);
	$stateProvider.state(createUserState);
	$stateProvider.state(updateRoleState);
	$stateProvider.state(createRoleState);
	
	$stateProvider.state(deviceAttributeManagementState);
	$stateProvider.state(createAndUpdateDeviceAttributeState);
	$stateProvider.state(deviceManagementState);
	$stateProvider.state(createAndUpdateDeviceState);
	$stateProvider.state(deviceMonitorState);
	$stateProvider.state(deviceDataAnalysisState);
	
	$stateProvider.state(productCategoryManagementState);
	$stateProvider.state(createProductCategoryState);
	$stateProvider.state(updateProductCategoryState);
	$stateProvider.state(updateProductState);
	$stateProvider.state(updateProductPriceState);
	$stateProvider.state(productManagementState);
	$stateProvider.state(allProductManagementState);
	$stateProvider.state(createProductState);
	
	$stateProvider.state(attributeManagementState);
	$stateProvider.state(createAndUpdateAttributeState);
	
	$stateProvider.state(productPackageTypeManagementState);
	$stateProvider.state(createAndUpdateProductPackageTypeState);
	
	$stateProvider.state(productManufacturingProcessState);
	$stateProvider.state(formulaManagementState);
	$stateProvider.state(updateFormulaState);
	$stateProvider.state(createFormulaState);
	
	
	
	$stateProvider.state(aboutState);

});

ineuronApp.controller('NavMenuController', ['$scope', function($scope) {
	
	$scope.hasPermission = function(funcId) {
		return hasPermission(funcId);
	}
	
}]);

ineuronApp.controller('AccountSettingController', ['$scope', '$cookies', function($scope, $cookies) {
	
	$scope.displayUsername=$cookies.get("INeuron-UserName", {path : "/"});
	
	$scope.logout = function() {
		$cookies.remove("INeuron-ApiToken", {path : "/"});
		$cookies.remove("INeuron-ProductSearchText", {path : "/"});
		$cookies.remove("INeuron-UserId", {path : "/"});
		$cookies.remove("INeuron-UserName", {path : "/"});
		$cookies.remove("INeuron-allPermissions", {path : "/"});
		$cookies.remove("INeuron-roleList", {path : "/"});
		window.location.href = "/ineuron/user/index.html/#/login";
	}
	
}]);


ineuronApp.controller('FrontpageController', ['$scope', '$timeout', function($scope, $timeout) {
	
	$scope.valueMaterial = 80;
	$scope.valueProduction = 80;
	$scope.valueDevice = 80;
	$scope.valueInventory = 80;
    $scope.upperLimit = 100;
    $scope.lowerLimit = 0;
    $scope.unit = "%";
    $scope.precision = 2;
    $scope.ranges = [
        {
            min: 0,
            max: 10,
            color: '#FDC702'
        },
        {
            min: 10,
            max: 90,
            color: '#8DCA2F'
        },
        {
            min: 90,
            max: 100,
            color: '#C50200'
        }
    ];
	
}]);

