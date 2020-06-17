#Windows10的OpenSSH服务===
&emsp;&emsp;密码多了容易忘记，前几天装了个Oracle11g，20几天没用，管理员的密码记不起来了。费了半天的劲才找了回来。于是乎突发灵感：如果有那天的操作日志就好了。cmd.exe是没戏了，没有这个功能。  
&emsp;&emsp;在Windows操作系统上执行的Tera Term是可以自动保存日志的。它支持的通信协议有SSH、telnet、串行通信（serial）。  
[下载地址1](https://zh.osdn.net/projects/ttssh2/downloads/72009/teraterm-4.105.zip/)：  
https://zh.osdn.net/projects/ttssh2/downloads/72009/teraterm-4.105.zip/  
下载地址2：  
[①teraterm-4.105.zip](https://pan.baidu.com/s/1a1krs0Bcg-xvmyvtt5q4Ow)：  
链接：https://pan.baidu.com/s/1a1krs0Bcg-xvmyvtt5q4Ow  
提取码：2t0v  
[②teraterm-4.105.exe](https://pan.baidu.com/s/1Hpj1l3IbJlnusXtbbwxl5Q)：  
链接：https://pan.baidu.com/s/1Hpj1l3IbJlnusXtbbwxl5Q  
提取码：t4hp  

###◆日志的设定：  
设置Setup菜单>additional settings子菜单，就会弹出下面的窗口。  
![additional settings子菜单](https://github.com/dubenju/repository/blob/master/pic/OpenSSH_001.jpg)

###◆选择Log选项卡  
![Log选项卡](https://github.com/dubenju/repository/blob/master/pic/OpenSSH_002.jpg)
 
显示Log的编辑器：C:\Windows\notepad.exe  
文件名：%Y%m%d_%H%M%S_&h_&p.log  
&h 主机名。未连接的时候为空。  
&p TCP/SSH端口号。未连接的时候为空。没有TCP/SSH连接的时候也是空的。  
%a 星期的省略形式。  
%A 星期的正式名。  
%b 月的省略形式。  
%B 月的正式名。  
%c 本地的日期和时间的表现。  
%d 用十进制表示的月份日期(01 ～ 31)。  
%H 24小时标记的时间(00 ～ 23)。  
%I 12小时标记的时间(01 ～ 12)。  
%j 用十进制表示的从年初开始的天数(001 ～ 366)。  
%m 用十进制表示的月(01 ～ 12)。  
%M 用十进制表示的分(00 ～ 59)。  
%p 现在的本地的上午/下午。  
%S 用十进制表示秒(00 ～ 59)。  
%U 10进制表示的一周序列号。把星期日作为一周的第一天(00～53)。  
%w 10进制表示星期(0 ～ 6，星期日为0)。  
%W 10进制表示的一周序列号。把星期一作为一周的第一天(00 ～ 53)。  
%x 现在本地的日期表现。  
%X 现在的本地时间表现。  
%y 10进制表示公历的下两位数(00 ～ 99)。  
%Y 用十进制表示的4位公历。  
%z, 根据%Z注册表的设置，指定时区的名称或省略形式。  
时间 区域不明确的情况下不指定。  
%% 百分比符号  
默认的保存目录：C:\teralog  

###◆最后，Setup>Save setup…保存。  
![保存](https://github.com/dubenju/repository/blob/master/pic/OpenSSH_003.jpg)

###◆编辑宏文件localhost.ttl  
connect 'localhost:22 /ssh /auth=password /user=youruser /passwd=userpassword'

###◆把宏文件追加到快捷菜单中：  
![追加到快捷菜单](https://github.com/dubenju/repository/blob/master/pic/OpenSSH_004.jpg)  

###设置对话框  
![设置对话框](https://github.com/dubenju/repository/blob/master/pic/OpenSSH_005.jpg)  
指定刚才编辑的宏文件C:\Program Files (x86)\teraterm\localhost.ttl  

客户端算是设置完了，服务器呢？  

###◆安装openssh  
设置>应用>应用和功能>管理可选功能>添加功能  
安装OpenSSH服务器和客户端，提示需要重启完成安装，重启电脑。  
![安装openssh](https://github.com/dubenju/repository/blob/master/pic/OpenSSH_006.jpg)

###◆验证安装  
在cmd界面输入ssh，验证客户端  

ssh  
usage: ssh [-46AaCfGgKkMNnqsTtVvXxYy] [-b bind_address] [-c cipher_spec]  
           [-D [bind_address:]port] [-E log_file] [-e escape_char]  
           [-F configfile] [-I pkcs11] [-i identity_file]  
           [-J [user@]host[:port]] [-L address] [-l login_name] [-m mac_spec]  
           [-O ctl_cmd] [-o option] [-p port] [-Q query_option] [-R address]  
           [-S ctl_path] [-W host:port] [-w local_tun[:remote_tun]]  
           destination [command]  

###◆SSH服务  
同样是命令行界面  

启动SSH服务net start sshd  
net start sshd  
OpenSSH SSH Server 服务正在启动。  
OpenSSH SSH Server 服务已经启动成功。  

停止SSH服务net stop sshd  
net stop sshd  
OpenSSH SSH Server 服务已成功停止。  

###◆远程SSH  
如果是本机请先开启SSH服务，大概如下：  
命令ssh username@127.0.0.1即ssh 用户名@用户ip  

ssh  username@用户ip  
username@用户ippassword:  
Microsoft Windows [版本]  
(c) 2019 Microsoft Corporation。保留所有权利。  

###◆我们的怎么执行呢？  
Ctrl+Alt+M>localhost就OK了。  
美中不足：有的图形界面程序比如画板通过teraterm就启动不了了。  

###◆后记  
本来是想安装telnet服务来着，可是win10环境下Telnet服务端已经没了。可以依靠第三方telnet服务来继续维持。  
http://www.goodtechsys.com/zip/TelnetdSetup.exe  
这个软件只能免费使用 30 天，要么买正版长期使用要么到此为止。  
https://www.georgiasoftworks.com/telnet-server-windows  
https://techwiser.com/enable-telnet-server-in-windows-10/  
开源KpyM Telnet/SSH Server：http://www.kpym.com/2/kpym/download.htm  
伊朗程序员开发的hk-telnet-server https://sourceforge.net/projects/hk-telnet-server/files/  
既然要被取代了，还是算了吧。  
