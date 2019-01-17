rem 连接服务器
echo "正在连接服务器。。"
net use \\172.245.71.13\ipc$ "rYZ9SCq66dJ5efnR13" /user:"root"
@echo off
echo 正在更新项目信息。。
rem 更新项目信息
svn update /opt/java/billBook
rem 关闭项目进程
kill -9 $(ps -ef|grep billBook.jar|gawk '$0 !~/grep/ {print $2}' |tr -s '\n' ' ')
echo 正在重新启动项目。。
rem 启动项目
nohup  java -jar /opt/java/lifeBook/lifebook.jar &

echo 重启完成。。
echo. & pause