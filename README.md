# Bunda

This is the repository of Indonesia version of WHO ANC (Antenatal Care) Android application.

## Developer Guide

After cloning this repository to local machine, you need to add two configuration files that are not included in this repository regarding security matters. Those files are:

1. **local.properties** - a file that stores information about the OpenSRP backend server
2. **reference-app/google-services.json** - a file that stores settings of the Google Services

Please consult with the administrator of OpenSRP backend server for these requirements.

Example of **local.properties** file content:
```
sdk.dir=/path/to/android/sdk
oauth.client.id="application-id"
oauth.client.secret="12345678-1234-5678-1234-123456789123"
flurry.api.key="12345678901234567890"
```

Example of **reference-app/google-services.json** file content:

```json
{
	"project_info": {
		"project_number": "123456789012",
		"firebase_url": "https://12345.firebaseio.com",
		"project_id": "project-id",
		"storage_bucket": "12345.appspot.com"
	},
	"client": [
		{
			"client_info": {
				"mobilesdk_app_id": "supersecret_mobilesdk_app)id",
				"android_client_info": {
					"package_name": "org.smartregister.anc"
				}
			},
			"oauth_client": [
				{
					"client_id": "supersecret_oauth_clientid",
					"client_type": 3
				}
			],
			"api_key": [
				{
					"current_key": "supersecret_api_key"
				}
			],
			"services": {
				"appinvite_service": {
					"other_platform_oauth_client": [
						{
							"client_id": "supersecret_oauth_clientid",
							"client_type": 3
						}
					]
				}
			}
		}
	],
	"configuration_version": "1"
}
```