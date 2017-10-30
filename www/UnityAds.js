module.exports = {

	init : (gameId) => {
		cordova.exec(undefined, undefined, 'CordovaUnityAdsPlugin', 'init', [gameId]);
	},
	
	setTestMode : (isTestMode) => {
		cordova.exec(undefined, undefined, 'CordovaUnityAdsPlugin', 'setTestMode', [isTestMode]);
	},
	
	checkIsCanShow : (callback) => {
		cordova.exec((ret) => {
			callback(ret === 1);
		}, undefined, 'CordovaUnityAdsPlugin', 'checkIsCanShow', []);
	},
	
	show : (callback) => {
		cordova.exec((ret) => {
			callback(ret === 1);
		}, undefined, 'CordovaUnityAdsPlugin', 'show', []);
	}
};