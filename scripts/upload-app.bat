SET APP_PATH="file=@%cd%\app\build\outputs\apk\app-debug.apk"

curl -k -X POST https://sales.experitest.com/api/v1/applications/new -H "Authorization: Basic bml2aTpYeGlvNjYzdGxz" -H "Cache-Control: no-cache" -H "content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW" -F %APP_PATH%