cordova.define("cordova/plugins/OpenNative",function(require, exports, module) {

	var exec = require("cordova/exec");

	var OpenNative = function() {};

	OpenNative.prototype.openActivity = function(className,isForResult,params,successCallback, errorCallback) {

		if (errorCallback == null) { errorCallback = function() {}}

		if (typeof errorCallback != "function" || typeof successCallback != "function" || !className||!isForResult||!params)  {

			errorCallback('openActivity参数错误！');

			return;

		}

		exec(successCallback, errorCallback, 'openNative', 'openActivity', [{"className":className,"isForResult":isForResult,"params":params}]);

	};

	OpenNative.prototype.openPDF = function(url,successCallback, errorCallback) {

		if (errorCallback == null) { errorCallback = function() {}}

		if (typeof errorCallback != "function" || typeof successCallback != "function" || !url)  {

			errorCallback('openPDF参数错误！');

			return;

		}

		exec(successCallback, errorCallback, 'openNative', 'openPDF', [{"url":url}]);

	};

    module.exports = new OpenNative();

});


if(!window.plugins) window.plugins = {};

if (!window.plugins.OpenNative) window.plugins.OpenNative = cordova.require("cordova/plugins/OpenNative");
