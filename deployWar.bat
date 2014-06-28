call %TOMCAT_FOLDER%\bin\shutdown.bat
set TOMCAT_FOLDER=E:\ALUAN\SoftWare\apache-tomcat-6.0.35
set WAR_TARGET="E:\ALUAN\Part_time\taphoa\target\ROOT.war"
set ZIP_EXE="C:\Program Files\WinRAR\WinRAR.exe"
rmdir /S /Q %TOMCAT_FOLDER%\webapps\ROOT
del %TOMCAT_FOLDER%\webapps\ROOT.war
call xcopy /s %WAR_TARGET% %TOMCAT_FOLDER%\Webapps


call %TOMCAT_FOLDER%\bin\startup.bat