###### Themed App Icon
1. Make sure to download the icon as SVG and not PNG!

2. open the Resource Manager tab (on left-side panel), 
click the ‘+’ button, and click Image Asset. 
In the Foreground Layer tab, choose “Image” as the Asset Type. 
Click the Browse icon and choose the icon that you have prepared 

3. Click the Next button, click the Finish button. 
Your launcher icons would be generated inside the mipmap folder. 
You need to make sure that there are XML files that are generated named ic_launcher.xml and ic_launcher_round.xml.

4. Lastly, Edit these XML files, specifically adding <monochrome> tag/element. 
This is how both ic_launcher.xml and ic_launcher_round.xml should look like now:

<sub>
<!-- This is how both "anydpi-v26/ic_launcher.xml" and "anydpi-v26/ic_launcher_round.xml" should look like. -->
<?xml version="1.0" encoding="utf-8"?>
<adaptive-icon xmlns:android="http://schemas.android.com/apk/res/android">
    <background android:drawable="@drawable/ic_launcher_background"/>
    <foreground android:drawable="@drawable/ic_launcher_foreground"/>
    <monochrome android:drawable="@drawable/ic_launcher_foreground"/>
</adaptive-icon>
</sub>

5. Enable Themed Icons option(by long-clicking your wallpaper and go to Wallpaper & Styles option), 
App’s launcher icon will be tinted according to your current wallpaper/theme like this: