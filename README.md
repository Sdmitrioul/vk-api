# VK-API

All about vk api you can read [here](https://dev.vk.com/reference).
You need to create your app and get your `APP_ID`.\
After will get your `VK_ACCESS_TOKEN`, if you go by this link `https://oauth.vk.com/authorize?client_id=<APP_ID>&display=page,post&redirect_uri=https://oauth.vk.com/blank.html&scope=friends&response_type=token&v=5.131`.
*Don't forget to add your `APP_ID` to link.*

Then add `.env` file with your params to root directory, you can see example [here](.env.template).
After you can launch by **command**: ```gradle run --args="<tag> <count_of_hours>"```.

This app is example of how to work with Mock and Stubs while testing. Architecture was developed using **SOLID** principles.