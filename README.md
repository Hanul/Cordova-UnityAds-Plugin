# Cordova UnityAds Plugin

```
cordova plugin add https://github.com/Hanul/Cordova-UnityAds-Plugin.git
```

Android 6에서 권한 설정 방식이 변경되어 아래 순서에 따라 권할 설정을 작성합니다.

1. [Android permission Cordova plugin](https://github.com/NeoLSN/cordova-plugin-android-permissions)을 설치
```
cordova plugin add cordova-plugin-android-permissions
```

2. 아래 내용 작성
```javascript
if (device.platform === 'Android') {
	
	let permissions = cordova.plugins.permissions;
	
	let needPermissions = [
		permissions.ACCESS_NETWORK_STATE,
		permissions.INTERNET,
		permissions.WRITE_EXTERNAL_STORAGE,
		permissions.READ_EXTERNAL_STORAGE
	];
	
	permissions.hasPermission(needPermissions, (status) => {
		if (status.hasPermission !== true) {
			
			let errorHandler = () => {
				
				alert(MSG({
					ko : '앱의 권한 설정에 오류가 있어 게임을 실행할 수 없습니다. 설정 - 앱 - Asteroid Girl에서 권한 설정을 완료해주시기 바랍니다. 추가 문의사항은 contact@btncafe.com으로 문의 바랍니다.'
				}));
				
				navigator.app.exitApp();
			};
			
			permissions.requestPermissions(needPermissions, (status) => {
				if (status.hasPermission !== true) {
					errorHandler();
				}
			}, errorHandler);
		}
	});

	UnityAds.init('1591358');
}

else {
	UnityAds.init('1591357');
}

UnityAds.setTestMode(true);
```