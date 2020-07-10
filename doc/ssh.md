Secure Shell
Secure Shell（安全外壳协议，简称SSH）是一种加密的网络传输协议，可在不安全的网络中为网络服务提供安全的传输环境。
SSH通过在网络中创建安全隧道来实现SSH客户端与服务器之间的连接。
SSH最常见的用途是远程登录系统，人们通常利用SSH来传输命令行界面和远程执行命令。
SSH使用频率最高的场合是类Unix系统，但是Windows操作系统也能有限度地使用SSH。
2015年，微软宣布将在未来的操作系统中提供原生SSH协议支持，Windows 10 1803版本已提供OpenSSH工具。

在设计上，SSH是Telnet和非安全shell的替代品。
Telnet和Berkeley rlogin、rsh、rexec等协议采用明文传输，使用不可靠的密码，容易遭到监听、嗅探和中间人攻击。
SSH旨在保证非安全网络环境（例如互联网）中信息加密完整可靠。

不过，SSH也被指出有被嗅探甚至解密的漏洞。早在2011年，中国的互联网审查机构已经有能力针对SSH连线的刺探及干扰。
而后爱德华·斯诺登泄露的文件也指出，美国国家安全局有时能够把SSH协议传输的信息解密出来，从而读出SSH会话的传输内容。
2017年7月6日，非营利组织维基解密确认美国中央情报局已经开发出能够在Windows或Linux操作系统中窃取SSH会话的工具。

概述
SSH以非对称加密实现身份验证。
身份验证有多种途径，例如其中一种方法是使用自动生成的公钥-私钥对来简单地加密网络连接，随后使用密码认证进行登录；
另一种方法是人工生成一对公钥和私钥，通过生成的密钥进行认证，这样就可以在不输入密码的情况下登录。
任何人都可以自行生成密钥。公钥需要放在待访问的电脑之中，而对应的私钥需要由用户自行保管。
认证过程基于生成出来的私钥，但整个认证过程中私钥本身不会传输到网络中。

SSH协议有两个主要版本，分别是SSH-1和SSH-2。
无论是哪个版本，核实未知密钥来源都是重要的事情，因为SSH只验证提供用户是否拥有与公钥相匹配的私钥，只要接受公钥而且密钥匹配服务器就会授予许可。
这样的话，一旦接受了恶意攻击者的公钥，那么系统也会把攻击者视为合法用户。

密钥管理
在类Unix系统中，已许可登录的公钥通常保存在用户 /home 目录的 ~/.ssh/authorized_keys 文件中，该文件只由SSH使用。
当远程机器持有公钥，而本地持有对应私钥时，登录过程不再需要手动输入密码。另外为了额外的安全性，私钥本身也能用密码保护。

私钥会保存在固定位置，也可以通过命令行参数指定（例如ssh命令的“-i”选项）。ssh-keygen是生成密钥的工具之一。

SSH也支持基于密码的身份验证，此时密钥是自动生成的。
若客户端和服务端从未进行过身份验证，SSH未记录服务器端所使用的密钥，那么攻击者可以模仿服务器端请求并获取密码，即中间人攻击。
但是密码认证可以禁用，而且SSH客户端在发现新密钥或未知服务器时会向用户发出警告。

应用
SSH的经典用途是登录到远程电脑中执行命令。除此之外，SSH也支持隧道协议、端口映射和X11连接。
借助SFTP或SCP协议，SSH还可以传输文件。

SSH使用客户端-服务器模型，标准端口为22。服务器端需要开启SSH守护进程以便接受远端的连接，而用户需要使用SSH客户端与其创建连接。

大多数现代操作系统（包括macOS、大部分Linux、OpenBSD、FreeBSD、Solaris等系统）都提供了SSH，包括Windows系统也提供SSH程序（在Windows 10 1809版本之后）。
在软件层次，许多关于SSH的专有软件、免费软件和开源软件被研发出来，如：
文件管理软件（同步、复制、删除等）。如：PuTTY和Windows下的WinSCP、类Unix系统下的Konqueror等。

SSH客户端。
从云计算的角度上讲，SSH能够阻止一些因直接暴露在互联网而产生的安全问题，在解决连接问题上发挥了重要作用。
SSH隧道可以在互联网、防火墙和虚拟机之间提供一个安全的通道。

历史
1.x版本
芬兰赫尔辛基理工大学的塔图·于勒宁发现自己学校存在嗅探密码的网络攻击，便于1995年编写了一套保护信息传输的程序，并称其为“secure shell”，简称SSH，设计目标是取代先前的rlogin、Telnet、FTP和rsh等安全性不足的协议。
1995年7月，于勒宁以免费软件的形式将其发布。程序很快流行起来，截至1995年底，SSH的用户数已经达到两万，遍布五十个国家。

1995年12月，于勒宁创立了SSH通信安全公司来继续开发和销售SSH。SSH的早期版本用到了很多自由软件，例如GNU libgmp，但后来由SSH公司发布的版本逐渐变成了专有软件。

截至2000年，已经有两百万用户使用SSH。

OpenSSH和OSSH
1999年，开发者们希望使用自由版本的SSH，于是重新使用较旧的1.2.12版本，这也是最后一个采用开放源代码许可的版本。
随后瑞典程序员Bjorn Gronvall基于这个版本开发了OSSH。
不久之后，OpenBSD的开发者又在Gronvall版本的基础上进行了大量修改，形成了OpenSSH，并于OpenBSD 2.6一起发行。
从该版本开始，OpenSSH又逐渐移植到了其他操作系统上面。

截至2005年，OpenSSH是唯一一种最流行的SSH实现，而且成为了大量操作系统的默认组件，而OSSH已经过时。
OpenSSH仍在维护，而且已经支持SSH-2协议。从7.6版开始，OpenSSH不再支持SSH-1协议。

2.x版本
2006年，SSH-2协议成为了新的标准。与SSH-1相比，SSH-2进行了一系列功能改进并增强了安全性，例如基于迪菲-赫尔曼密钥交换的加密和基于消息认证码的完整性检查。
SSH-2还支持通过单个SSH连接任意数量的shell会话。
SSH-2协议与SSH-1不兼容，由于更加流行，一些实现（例如lsh和Dropbear）只支持SSH-2协议。

1.99版
RFC 4253规定支持2.0及以前版本SSH的SSH服务器应将其原始版本标为“1.99”。“1.99”并不是实际的软件版本号，而是为了表示向下兼容。

基本架构
SSH协议框架中最主要的部分是三个协议：

传输层协议（The Transport Layer Protocol）：传输层协议提供服务器认证，数据机密性，信息完整性等的支持。
用户认证协议（The User Authentication Protocol）：用户认证协议为服务器提供客户端的身份鉴别。
连接协议（The Connection Protocol）：连接协议将加密的信息隧道复用成若干个逻辑通道，提供给更高层的应用协议使用。
同时还有为许多高层的网络安全应用协议提供扩展的支持。

各种高层应用协议可以相对地独立于SSH基本体系之外，并依靠这个基本框架，通过连接协议使用SSH的安全机制。

SSH的安全验证
在客户端来看，SSH提供两种级别的安全验证。
第一种级别（基于密码的安全验证），知道帐号和密码，就可以登录到远程主机，并且所有传输的数据都会被加密。但是，可能会有别的服务器在冒充真正的服务器，无法避免被“中间人”攻击。
第二种级别（基于密钥的安全验证），需要依靠密钥，也就是你必须为自己创建一对密钥，并把公有密钥放在需要访问的服务器上。客户端软件会向服务器发出请求，请求用你的密钥进行安全验证。服务器收到请求之后，先在你在该服务器的用户根目录下寻找你的公有密钥，然后把它和你发送过来的公有密钥进行比较。如果两个密钥一致，服务器就用公有密钥加密“质询”（challenge）并把它发送给客户端软件。从而避免被“中间人”攻击。
在服务器端，SSH也提供安全验证。 在第一种方案中，主机将自己的公用密钥分发给相关的客户端，客户端在访问主机时则使用该主机的公开密钥来加密数据，主机则使用自己的私有密钥来解密数据，从而实现主机密钥认证，确保数据的保密性。 在第二种方案中，存在一个密钥认证中心，所有提供服务的主机都将自己的公开密钥提交给认证中心，而任何作为客户端的主机则只要保存一份认证中心的公开密钥就可以了。在这种模式下，客户端必须访问认证中心然后才能访问服务器主机。

SSH协议的可扩展性
SSH协议框架中设计了大量可扩展项，比如用户自定义算法、客户自定义密钥规则、高层扩展功能性应用协议。这些扩展大多遵循IANA的有关规定，特别是在重要的部分，像命名规则和消息编码方面。


TCP端口（静态端口）
TCP 0= Reserved
TCP 1=TCP Port Service Multiplexer
TCP 2=Death
TCP 5=Remote Job Entry,yoyo
TCP 7=Echo
TCP 11=Skun
TCP 12=Bomber
TCP 16=Skun
TCP 17=Skun
TCP 18=消息传输协议，skun
TCP 19=Skun
TCP 20=FTP Data,Amanda
TCP 21=文件传输,Back Construction,Blade Runner,Doly Trojan,Fore,FTP trojan,Invisible FTP,Larva,WebEx,WinCrash
TCP 22=远程登录协议
TCP 23=远程登录（Telnet),Tiny Telnet Server (= TTS)
TCP 25=电子邮件(SMTP),Ajan,Antigen,Email Password Sender,Happy 99,Kuang2,ProMail trojan,Shtrilitz,Stealth,Tapiras,Terminator,WinPC,WinSpy,Haebu Coceda
TCP 27=Assasin
TCP 28=Amanda
TCP 29=MSG ICP
TCP 30=Agent 40421
TCP 31=Agent 31,Hackers Paradise,Masters Paradise,Agent 40421
TCP 37=Time,ADM worm
TCP 39=SubSARI
TCP 41=DeepThroat,Foreplay
TCP 42=Host Name Server
TCP 43=WHOIS
TCP 44=Arctic
TCP 48=DRAT
TCP 49=主机登录协议
TCP 50=DRAT
TCP 51=IMP Logical Address Maintenance,Fuck Lamers Backdoor
TCP 52=MuSka52,Skun
TCP 53=DNS,Bonk (DOS Exploit)
TCP 54=MuSka52
TCP 58=DMSetup
TCP 59=DMSetup
TCP 63=whois++
TCP 64=Communications Integrator
TCP 65=TACACS-Database Service
TCP 66=Oracle SQL*NET,AL-Bareki
TCP 67=Bootstrap Protocol Server
TCP 68=Bootstrap Protocol Client
TCP 69=TFTP,W32.Evala.Worm,BackGate Kit,Nimda,Pasana,Storm,Storm worm,Theef,Worm.Cycle.a
TCP 70=Gopher服务，ADM worm
TCP 79=用户查询（Finger),Firehotcker,ADM worm
TCP 80=超文本服务器（Http),Executor,RingZero
TCP 81=Chubo,Worm.Bbeagle.q
TCP 82=Netsky-Z
TCP 88=Kerberos krb5服务
TCP 99=Hidden Port
TCP 102=消息传输代理
TCP 108=SNA网关访问服务器
TCP 109=Pop2
TCP 110=电子邮件（Pop3),ProMail
TCP 113=Kazimas,Auther Idnet
TCP 115=简单文件传输协议
TCP 118=SQL Services,Infector 1.4.2
TCP 119=新闻组传输协议（Newsgroup(Nntp)),Happy 99
TCP 121=JammerKiller,Bo jammerkillah
TCP 123=网络时间协议(NTP),Net Controller
TCP 129=Password Generator Protocol
TCP 133=Infector 1.x
TCP 135=微软DCE RPC end-point mapper服务
TCP 137=微软Netbios Name服务（网上邻居传输文件使用）
TCP 138=微软Netbios Name服务（网上邻居传输文件使用）
TCP 139=微软Netbios Name服务（用于文件及打印机共享）
TCP 142=NetTaxi
TCP 143=Internet 邮件访问协议版本 4（IMAP4)
TCP 146=FC Infector,Infector
TCP 150=NetBIOS Session Service
TCP 156=SQL服务器
TCP 161=Snmp
TCP 162=Snmp-Trap
TCP 170=A-Trojan
TCP 177=X Display管理控制协议
TCP 179=Border网关协议（BGP)
TCP 190=网关访问控制协议（GACP)
TCP 194=Irc
TCP 197=目录定位服务（DLS)
TCP 220=Internet 邮件访问协议版本 3（IMAP3)
TCP 256=Nirvana
TCP 315=The Invasor
TCP 371=ClearCase版本管理软件
TCP 389=Lightweight Directory Access Protocol (LDAP)
TCP 396=Novell Netware over IP
TCP 420=Breach
TCP 421=TCP Wrappers
TCP 443=安全服务（HTTPS）
TCP 444=Simple Network Paging Protocol(SNPP)
TCP 445=Microsoft-DS
TCP 455=Fatal Connections
TCP 456=Hackers paradise,FuseSpark
TCP 458=苹果公司QuickTime
TCP 513=Grlogin
TCP 514=RPC Backdoor
UDP 520=Rip
TCP 531=Rasmin,Net666
TCP 544=kerberos kshell
TCP 546=DHCP Client
TCP 547=DHCP Server
TCP 548=Macintosh文件服务
TCP 555=Ini-Killer,Phase Zero,Stealth Spy
TCP 569=MSN
TCP 605=SecretService
TCP 606=Noknok8
TCP 660=DeepThroat
TCP 661=Noknok8
TCP 666=Attack FTP,Satanz Backdoor,Back Construction,Dark Connection Inside 1.2
TCP 667=Noknok7.2
TCP 668=Noknok6
TCP 669=DP trojan
TCP 692=GayOL
TCP 707=Welchia,nachi
TCP 777=AIM Spy
TCP 808=RemoteControl,WinHole
TCP 815=Everyone Darling
TCP 901=Backdoor.Devil
TCP 911=Dark Shadow
TCP 990=ssl加密
TCP 993=IMAP
TCP 999=DeepThroat
TCP 1000=Der Spaeher
TCP 1001=Silencer,WebEx,Der Spaeher
TCP 1003=BackDoor
TCP 1010=Doly
TCP 1011=Doly
TCP 1012=Doly
TCP 1015=Doly
TCP 1016=Doly
TCP 1020=Vampire
TCP 1023=Worm.Sasser.e
TCP端口（动态端口）
TCP 1024=NetSpy.698(YAI)
TCP 1025=NetSpy.698,Unused Windows Services Block
TCP 1026=Unused Windows Services Block
TCP 1027=Unused Windows Services Block
TCP 1028=Unused Windows Services Block
TCP 1029=Unused Windows Services Block
TCP 1030=Unused Windows Services Block
TCP 1033=Netspy
TCP 1035=Multidropper
TCP 1042=Bla
TCP 1045=Rasmin
TCP 1047=GateCrasher
TCP 1050=MiniCommand
TCP 1059=nimreg
TCP 1069=Backdoor.TheefServer.202
TCP 1070=Voice,Psyber Stream Server,Streaming Audio Trojan
TCP 1080=Wingate,Worm.BugBear.B,Worm.Novarg.B
TCP 1090=Xtreme,VDOLive
TCP 1092=LoveGate
TCP 1095=Rat
TCP 1097=Rat
TCP 1098=Rat
TCP 1099=Rat
TCP 1110=nfsd-keepalive
TCP 1111=Backdoor.AIMVision
TCP 1155=Network File Access
TCP 1170=Psyber Stream Server,Streaming Audio trojan,Voice
TCP 1200=NoBackO
TCP 1201=NoBackO
TCP 1207=Softwar
TCP 1212=Nirvana,Visul Killer
TCP 1234=Ultors
TCP 1243=BackDoor-G,SubSeven,SubSeven Apocalypse
TCP 1245=VooDoo Doll
TCP 1269=Mavericks Matrix
TCP 1313=Nirvana
TCP 1349=BioNet
TCP 1433=Microsoft SQL服务
TCP 1441=Remote Storm
TCP 1492=FTP99CMP(BackOriffice.FTP)
TCP 1503=NetMeeting T.120
TCP 1509=Psyber Streaming Server
TCP 1600=Shivka-Burka
TCP 1688=Key Management Service(密钥管理服务)
TCP 1703=Exloiter 1.1
TCP 1720=NetMeeting H.233 call Setup
TCP 1723=VPN 网关（PPTP）
TCP 1731=NetMeeting音频调用控制
TCP 1807=SpySender
TCP 1966=Fake FTP 2000
TCP 1976=Custom port
TCP 1981=Shockrave
TCP 1990=stun-p1 cisco STUN Priority 1 port
TCP 1990=stun-p1 cisco STUN Priority 1 port
TCP 1991=stun-p2 cisco STUN Priority 2 port
TCP 1992=stun-p3 cisco STUN Priority 3 port,ipsendmsg IPsendmsg
TCP 1993=snmp-tcp-port cisco SNMP TCP port
TCP 1994=stun-port cisco serial tunnel port
TCP 1995=perf-port cisco perf port
TCP 1996=tr-rsrb-port cisco Remote SRB port
TCP 1997=gdp-port cisco Gateway Discovery Protocol
TCP 1998=x25-svc-port cisco X.25 service (XOT)
TCP 1999=BackDoor,TransScout
TCP 2000=Der Spaeher,INsane Network
TCP 2002=W32. Beagle .AX @mm
TCP 2001=Transmisson scout
TCP 2002=Transmisson scout
TCP 2003=Transmisson scout
TCP 2004=Transmisson scout
TCP 2005=TTransmisson scout
TCP 2011=cypress
TCP 2015=raid-cs
TCP 2023=Ripper,Pass Ripper,Hack City Ripper Pro
TCP 2049=NFS
TCP 2115=Bugs
TCP 2121=Nirvana
TCP 2140=Deep Throat,The Invasor
TCP 2155=Nirvana
TCP 2208=RuX
TCP 2255=Illusion Mailer
TCP 2283=HVL Rat5
TCP 2300=PC Explorer
TCP 2311=Studio54
TCP 2556=Worm.Bbeagle.q
TCP 2565=Striker
TCP 2583=WinCrash
TCP 2600=Digital RootBeer
TCP 2716=Prayer Trojan
TCP 2745=Worm.BBeagle.k
TCP 2773=Backdoor,SubSeven
TCP 2774=SubSeven2.1&2.2
TCP 2801=Phineas Phucker
TCP 2989=Rat
TCP 3024=WinCrash trojan
TCP 3127=Worm.Novarg
TCP 3128=RingZero,Worm.Novarg.B
TCP 3129=Masters Paradise
TCP 3150=Deep Throat,The Invasor
TCP 3198=Worm.Novarg
TCP 3210=SchoolBus
TCP 3332=Worm.Cycle.a
TCP 3333=Prosiak
TCP 3389=超级终端（远程桌面）
TCP 3456=Terror
TCP 3459=Eclipse 2000
TCP 3700=Portal of Doom
TCP 3791=Eclypse
TCP 3801=Eclypse
TCP 3996=Portal of Doom,RemoteAnything
TCP 4000=腾讯QQ客户端
TCP 4060=Portal of Doom,RemoteAnything
TCP 4092=WinCrash
TCP 4242=VHM
TCP 4267=SubSeven2.1&2.2
TCP 4321=BoBo
TCP 4444=Prosiak,Swift remote
TCP 4500=W32.HLLW.Tufas
TCP 4567=File Nail
TCP 4590=ICQTrojan
TCP 4899=Remote Administrator服务器
TCP 4950=ICQTrojan
TCP 5000=WindowsXP服务器，Blazer 5,Bubbel,Back Door Setup,Sockets de Troie
TCP 5001=Back Door Setup,Sockets de Troie
TCP 5002=cd00r,Shaft
TCP 5011=One of the Last Trojans (OOTLT)
TCP 5025=WM Remote KeyLogger
TCP 5031=Firehotcker,Metropolitan,NetMetro
TCP 5032=Metropolitan
TCP 5190=ICQ Query
TCP 5321=Firehotcker
TCP 5333=Backage Trojan Box 3
TCP 5343=WCrat
TCP 5400=Blade Runner,BackConstruction1.2
TCP 5401=Blade Runner,Back Construction
TCP 5402=Blade Runner,Back Construction
TCP 5471=WinCrash
TCP 5512=Illusion Mailer
TCP 5521=Illusion Mailer
TCP 5550=Xtcp,INsane Network
TCP 5554=Worm.Sasser
TCP 5555=ServeMe
TCP 5556=BO Facil
TCP 5557=BO Facil
TCP 5569=Robo-Hack
TCP 5598=BackDoor 2.03
TCP 5631=PCAnyWhere data
TCP 5632=PCAnyWhere
TCP 5637=PC Crasher
TCP 5638=PC Crasher
TCP 5698=BackDoor
TCP 5714=Wincrash3
TCP 5741=WinCrash3
TCP 5742=WinCrash
TCP 5760=Portmap Remote Root Linux Exploit
TCP 5880=Y3K RAT
TCP 5881=Y3K RAT
TCP 5882=Y3K RAT
TCP 5888=Y3K RAT
TCP 5889=Y3K RAT
TCP 5900=WinVnc
TCP 6000=Backdoor.AB
TCP 6006=Noknok8
TCP 6129=Dameware Nt Utilities服务器
TCP 6272=SecretService
TCP 6267=广外女生
TCP 6400=Backdoor.AB,The Thing
TCP 6500=Devil 1.03
TCP 6661=Teman
TCP 6666=TCPshell.c
TCP 6667=NT Remote Control,Wise 播放器接收端口
TCP 6668=Wise Video广播端口
TCP 6669=Vampyre
TCP 6670=DeepThroat,iPhone
TCP 6671=Deep Throat 3.0
TCP 6711=SubSeven
TCP 6712=SubSeven1.x
TCP 6713=SubSeven
TCP 6723=Mstream
TCP 6767=NT Remote Control
TCP 6771=DeepThroat
TCP 6776=BackDoor-G,SubSeven,2000 Cracks
TCP 6777=Worm.BBeagle
TCP 6789=Doly Trojan
TCP 6838=Mstream
TCP 6883=DeltaSource
TCP 6912=Shit Heep
TCP 6939=Indoctrination
TCP 6969=GateCrasher,Priority,IRC 3
TCP 6970=RealAudio,GateCrasher
TCP 7000=Remote Grab,NetMonitor,SubSeven1.x
TCP 7001=Freak88
TCP 7201=NetMonitor
TCP 7215=BackDoor-G,SubSeven
TCP 7001=Freak88,Freak2k
TCP 7300=NetMonitor
TCP 7301=NetMonitor
TCP 7306=NetMonitor,NetSpy 1.0
TCP 7307=NetMonitor,ProcSpy
TCP 7308=NetMonitor,X Spy
TCP 7323=Sygate服务器端
TCP 7424=Host Control
TCP 7511=聪明基因
TCP 7597=Qaz
TCP 7609=Snid X2
TCP 7626=冰河
TCP 7777=The Thing
TCP 7789=Back Door Setup,ICQKiller
TCP 7983=Mstream
TCP 8000=腾讯OICQ服务器端，XDMA
TCP 8010=Wingate,Logfile
TCP 8011=WAY2.4
TCP 8080=WWW 代理，Ring Zero,Chubo,Worm.Novarg.B
TCP 8102=网络神偷
TCP
8181=W32.Erkez.D@mm
TCP 8520=W32.Socay.Worm
TCP 8594=I-Worm/Bozori.a
TCP 8787=BackOfrice 2000
TCP 8888=Winvnc
TCP 8897=Hack Office,Armageddon
TCP 8989=Recon
TCP 9000=Netministrator
TCP 9325=Mstream
TCP 9400=InCommand 1.0
TCP 9401=InCommand 1.0
TCP 9402=InCommand 1.0
TCP 9872=Portal of Doom
TCP 9873=Portal of Doom
TCP 9874=Portal of Doom
TCP 9875=Portal of Doom
TCP 9876=Cyber Attacker
TCP 9878=TransScout
TCP 9989=Ini-Killer
TCP 9898=Worm.Win32.Dabber.a
TCP 9999=Prayer Trojan
TCP 10067=Portal of Doom
TCP 10080=Worm.Novarg.B
TCP 10084=Syphillis
TCP 10085=Syphillis
TCP 10086=Syphillis
TCP 10101=BrainSpy
TCP 10167=Portal Of Doom
TCP 10168=Worm.Supnot.78858.c,Worm.LovGate.T
TCP 10520=Acid Shivers
TCP 10607=Coma trojan
TCP 10666=Ambush
TCP 11000=Senna Spy
TCP 11050=Host Control
TCP 11051=Host Control
TCP 11223=Progenic,Hack ’99KeyLogger
TCP 11831=TROJ_LATINUS.SVR
TCP 12076=Gjamer,MSH.104b
TCP 12223=Hack’99 KeyLogger
TCP 12345=GabanBus,NetBus 1.6/1.7,Pie Bill Gates,X-bill
TCP 12346=GabanBus,NetBus 1.6/1.7,X-bill
TCP 12349=BioNet
TCP 12361=Whack-a-mole
TCP 12362=Whack-a-mole
TCP 12363=Whack-a-mole
TCP12378=W32/Gibe@MM
TCP 12456=NetBus
TCP 12623=DUN Control
TCP 12624=Buttman
TCP 12631=WhackJob,WhackJob.NB1.7
TCP 12701=Eclipse2000
TCP 12754=Mstream
TCP 13000=Senna Spy
TCP 13010=Hacker Brazil
TCP 13013=Psychward
TCP 13223=Tribal Voice的聊天程序PowWow
TCP 13700=Kuang2 The Virus
TCP 14456=Solero
TCP 14500=PC Invader
TCP 14501=PC Invader
TCP 14502=PC Invader
TCP 14503=PC Invader
TCP 15000=NetDaemon 1.0
TCP 15092=Host Control
TCP 15104=Mstream
TCP 16484=Mosucker
TCP 16660=Stacheldraht (DDoS)
TCP 16772=ICQ Revenge
TCP 16959=Priority
TCP 16969=Priority
TCP 17027=提供广告服务的Conducent"adbot"共享软件
TCP 17166=Mosaic
TCP 17300=Kuang2 The Virus
TCP 17490=CrazyNet
TCP 17500=CrazyNet
TCP 17569=Infector 1.4.x + 1.6.x
TCP 17777=Nephron
TCP 18753=Shaft (DDoS)
TCP 19191=蓝色火焰
TCP 19864=ICQ Revenge
TCP 20000=Millennium II (GrilFriend)
TCP 20001=Millennium II (GrilFriend)
TCP 20002=AcidkoR
TCP 20034=NetBus 2 Pro
TCP 20168=Lovgate
TCP 20203=Logged,Chupacabra
TCP 20331=Bla
TCP 20432=Shaft (DDoS)
TCP 20808=Worm.LovGate.v.QQ
TCP 213 35=Tribal Flood Network,Trinoo
TCP 21544=Schwindler 1.82,GirlFriend
TCP 21554=Schwindler 1.82,GirlFriend,Exloiter 1.0.1.2
TCP 22222=Prosiak,RuXUploader2.0
TCP 22784=Backdoor.Intruzzo
TCP 23432=Asylum 0.1.3
TCP 23444=网络公牛
TCP 23456=Evil FTP,Ugly FTP,WhackJob
TCP 23476=Donald Dick
TCP 23477=Donald Dick
TCP 23777=INet Spy
TCP 26274=Delta
TCP 26681=Spy Voice
TCP 27374=Sub Seven 2.0+,Backdoor.Baste
TCP 27444=Tribal Flood Network,Trinoo
TCP 27665=Tribal Flood Network,Trinoo
TCP 29431=Hack Attack
TCP 29432=Hack Attack
TCP 29104=Host Control
TCP 29559=TROJ_LATINUS.SVR
TCP 29891=The Unexplained
TCP 30001=Terr0r32
TCP 30003=Death,Lamers Death
TCP 30029=AOL trojan
TCP 30100=NetSphere 1.27a,NetSphere 1.31
TCP 30101=NetSphere 1.31,NetSphere 1.27a
TCP 30102=NetSphere 1.27a,NetSphere 1.31
TCP 30103=NetSphere 1.31
TCP 30303=Sockets de Troie
TCP 30722=W32.Esbot.A
TCP 30947=Intruse
TCP 30999=Kuang2
TCP 31336=Bo Whack
TCP 31337=Baron Night,BO client,BO2,Bo Facil,BackFire,Back Orifice,DeepBO,Freak2k,NetSpy
TCP 31338=NetSpy,Back Orifice,DeepBO
TCP 31339=NetSpy DK
TCP 31554=Schwindler
TCP 31666=BOWhack
TCP 31778=Hack Attack
TCP 31785=Hack Attack
TCP 31787=Hack Attack
TCP 31789=Hack Attack
TCP 31791=Hack Attack
TCP 31792=Hack Attack
TCP 32100=PeanutBrittle
TCP 32418=Acid Battery
TCP 33333=Prosiak,Blakharaz 1.0
TCP 33577=Son Of Psychward
TCP 33777=Son Of Psychward
TCP 33911=Spirit 2001a
TCP 34324=BigGluck,TN,Tiny Telnet Server
TCP 34555=Trin00 (Windows) (DDoS)
TCP 35555=Trin00 (Windows) (DDoS)
TCP 36794=Worm.Bugbear-A
TCP 37651=YAT
TCP 40412=The Spy
TCP 40421=Agent 40421,Masters Paradise.96
TCP 40422=Masters Paradise
TCP 40423=Masters Paradise.97
TCP 40425=Masters Paradise
TCP 40426=Masters Paradise 3.x
TCP 41666=Remote Boot
TCP 43210=Schoolbus 1.6/2.0
TCP 44444=Delta Source
TCP 44445=Happypig
TCP 45576=未知代理
TCP 47252=Prosiak
TCP 47262=Delta
TCP 47878=BirdSpy2
TCP 49301=Online Keylogger
TCP 50505=Sockets de Troie
TCP 50766=Fore,Schwindler
TCP 51966=CafeIni
TCP 53001=Remote Windows Shutdown
TCP 53217=Acid Battery 2000
TCP 54283=Back Door-G,Sub7
TCP 54320=Back Orifice 2000,Sheep
TCP 54321=School Bus .69-1.11,Sheep,BO2K
TCP 57341=NetRaider
TCP 58008=BackDoor.Tron
TCP 58009=BackDoor.Tron
TCP 58339=ButtFunnel
TCP 59211=BackDoor.DuckToy
TCP 60000=Deep Throat
TCP 60068=Xzip 6000068
TCP 60411=Connection
TCP 60606=TROJ_BCKDOR.G2.A
TCP 61466=Telecommando
TCP 61603=Bunker-kill
TCP 63485=Bunker-kill
TCP 65000=Devil,DDoS
TCP 65432=Th3tr41t0r,The Traitor
TCP 65530=TROJ_WINMITE.10
TCP 65535=RC,Adore Worm/Linux
 
 
UDP端口（静态端口）
UDP 1=Sockets des Troie
UDP 9=Chargen
UDP 19=Chargen
UDP 69=Pasana
UDP 80=Penrox
UDP 371=ClearCase版本管理软件
UDP 445=公共Internet文件系统（CIFS)
UDP 500=Internet密钥交换（IP安全性 ,IKE)
UDP端口（动态端口）
UDP 1025=Maverick’s Matrix 1.2 - 2.0
UDP 1026=Remote Explorer 2000
UDP 1027=UC聊天软件，Trojan.Huigezi.e
UDP 1028=3721上网助手（用途不明，建议用户警惕！），KiLo,SubSARI
UDP 1029=SubSARI
UDP 1031=Xot
UDP 1032=Akosch4
UDP 1104=RexxRave
UDP 1111=Daodan
UDP 1116=Lurker
UDP 1122=Last 2000,Singularity
UDP 1183=Cyn,SweetHeart
UDP 1200=NoBackO
UDP 1201=NoBackO
UDP 1342=BLA trojan
UDP 1344=Ptakks
UDP 1349=BO dll
UDP 1561=MuSka52
UDP 1701=VPN网关（L2TP）
UDP 1772=NetControle
UDP 1978=Slapper
UDP 1985=Black Diver
UDP 2000=A-trojan,Fear,Force,GOTHIC Intruder,Last 2000,Real 2000
UDP 2001=Scalper
UDP 2002=Slapper
UDP 2015=raid-cs
UDP 2018=rellpack
UDP 2130=Mini BackLash
UDP 2140=Deep Throat,Foreplay,The Invasor
UDP 2222=SweetHeart,Way
UDP 2339=Voice Spy
UDP 2702=Black Diver
UDP 2989=RAT
UDP 3150=Deep Throat
UDP 3215=XHX
UDP 3333=Daodan
UDP 3801=Eclypse
UDP 3996=Remote Anything
UDP 4128=RedShad
UDP 4156=Slapper
UDP 4500=sae-urn/ (IP安全性，IKE NAT遍历）
UDP 5419=DarkSky
UDP 5503=Remote Shell Trojan
UDP 5555=Daodan
UDP 5882=Y3K RAT
UDP 5888=Y3K RAT
UDP 6112=Battle .net Game
UDP 6666=KiLo
UDP 6667=KiLo
UDP 6766=KiLo
UDP 6767=KiLo,UandMe
UDP 6838=Mstream Agent-handler
UDP 7028=未知木马
UDP 7424=Host Control
UDP 7788=Singularity
UDP 7983=MStream handler-agent
UDP 8012=Ptakks
UDP 8090=Aphex’s Remote Packet Sniffer
UDP 8127=9_119,Chonker
UDP 8488=KiLo
UDP 8489=KiLo
UDP 8787=BackOrifice 2000
UDP 8879=BackOrifice 2000
UDP 9325=MStream Agent-handler
UDP 10000=XHX
UDP 10067=Portal of Doom
UDP 10084=Syphillis
UDP 10100=Slapper
UDP 10167=Portal of Doom
UDP 10498=Mstream
UDP 10666=Ambush
UDP 11225=Cyn
UDP 12321=Protoss
UDP 12345=BlueIce 2000
UDP12378=W32/Gibe@MM
UDP 12623=ButtMan,DUN Control
UDP 15210=UDP remote shell backdoor server
UDP 15486=KiLo
UDP 16514=KiLo
UDP 16515=KiLo
UDP 18753=Shaft handler to Agent
UDP 20433=Shaft
UDP 21554=GirlFriend
UDP 22784=Backdoor.Intruzzo
UDP 23476=Donald Dick
UDP 25123=MOTD
UDP 26274=Delta Source
UDP 26374=Sub-7 2.1
UDP 26444=Trin00/TFN2K
UDP 26573=Sub-7 2.1
UDP 27184=Alvgus trojan 2000
UDP 27444=Trinoo
UDP 29589=KiLo
UDP 29891=The Unexplained
UDP 30103=NetSphere
UDP 31320=Little Witch
UDP 31335=Trin00 DoS Attack
UDP 31337=Baron Night,BO client,BO2,Bo Facil,BackFire,Back Orifice,DeepBO
UDP 31338=Back Orifice,NetSpy DK,DeepBO
UDP 31339=Little Witch
UDP 31340=Little Witch
UDP 31416=Lithium
UDP 31787=Hack aTack
UDP 31789=Hack aTack
UDP 31790=Hack aTack
UDP 31791=Hack aTack
UDP 33390=未知木马
UDP 34555=Trinoo
UDP 35555=Trinoo
UDP 43720=KiLo
UDP 44014=Iani
UDP 44767=School Bus
UDP 46666=Taskman
UDP 47262=Delta Source
UDP 47785=KiLo
UDP 49301=OnLine keyLogger
UDP 49683=Fenster
UDP 49698=KiLo
UDP 52901=Omega
UDP 54320=Back Orifice
UDP 54321=Back Orifice 2000
UDP 54341=NetRaider Trojan
UDP 61746=KiLO
UDP 61747=KiLO
UDP 61748=KiLO
UDP 65432=The Traitor

smtp:25
POP3:110
SMTPS:25
POP3S:995
ISA/TMG:465
EXCHANGE:587
如果是465端口，需要加上smtps://协议；如果是587端口，不需要加smtps://或者写smtp://；
如果使用587端口通讯，应当显示设置smtp-use-starttls；
有些邮件服务器的587端口不是使用STARTTLS而是SMTPS，此时仍需加上smtps://协议，例如126邮箱。

telnet smtp.163.com 25
telnet smtp.163.com 465

SMTP发件服务器地址：smtp.126.com 安全：开（SSL/TLS） 端口号：465 / 994
注：1、163邮箱的SMTP服务器地址：smtp.163.com，yeah邮箱的SMTP服务器地址：smtp.yeah.net；
2、若安全选择关闭，请将端口号改为 25。

ｓmtp.gmail.com
要求 SSL：是
要求 TLS：是（如适用）
使用身份验证：是
SSL 端口：465
TLS/STARTTLS 端口：587


C:\Users\benju>where keytool
C:\jdk1.8.0_202\bin\keytool.exe

C:\Users\benju>keytool -genkeypair
キーストアのパスワードを入力してください:
新規パスワードを再入力してください:
姓名は何ですか。
  [Unknown]:  dbj
組織単位名は何ですか。
  [Unknown]:  dbj
組織名は何ですか。
  [Unknown]:  dbj
都市名または地域名は何ですか。
  [Unknown]:  dbj
都道府県名または州名は何ですか。
  [Unknown]:  dbj
この単位に該当する2文字の国コードは何ですか。
  [Unknown]:  jp
CN=dbj, OU=dbj, O=dbj, L=dbj, ST=dbj, C=jpでよろしいですか。
  [いいえ]:
姓名は何ですか。
  [dbj]:
組織単位名は何ですか。
  [dbj]:
組織名は何ですか。
  [dbj]:
都市名または地域名は何ですか。
  [dbj]:
都道府県名または州名は何ですか。
  [dbj]:
この単位に該当する2文字の国コードは何ですか。
  [jp]:
CN=dbj, OU=dbj, O=dbj, L=dbj, ST=dbj, C=jpでよろしいですか。
  [いいえ]:  y

<mykey>の鍵パスワードを入力してください
        (キーストアのパスワードと同じ場合はRETURNを押してください):

Warning:
JKSキーストアは独自の形式を使用しています。"keytool -importkeystore -srckeystore C:\Users\benju\.keystore -destkeystore C:\Users\benju\.keystore -deststoretype pkcs12"を使用する業界標準の形式であるPKCS12に移行することをお薦めします。

C:\Users\benju>
C:\Users\benju>keytool -genkeypair -alias certificate -keystore my_keystore.pfx -storepass password -validity 365 -keyalg RSA -keysize 2048 -storetype pkcs12
姓名は何ですか。
  [Unknown]:  dbj
組織単位名は何ですか。
  [Unknown]:  dbj
組織名は何ですか。
  [Unknown]:  dbj
都市名または地域名は何ですか。
  [Unknown]:  dbj
都道府県名または州名は何ですか。
  [Unknown]:  dbj
この単位に該当する2文字の国コードは何ですか。
  [Unknown]:  jp
CN=dbj, OU=dbj, O=dbj, L=dbj, ST=dbj, C=jpでよろしいですか。
  [いいえ]:  y


C:\Users\benju>

1


I've downloaded apache james 2.3.2. At the moment I just want to get it working on localhost with ssl encyption (but I know very little about ssl). 
Initially I managed to get it running, create accounts (by running james-2.3.2/bin/run.bat) and connect to these account using mozilla thunderbird mail client. 
The trouble comes when I try to create a self signed ssl certificate. I have tried two different methods.

running keytool -genkeypair creates a keystore in .keystore in my home directory and

running keytool -genkeypair -alias certificate -keystore my_keystore.pfx -storepass password -validty 365 -keyalg RSA -keysize 2048 -storetype pkcs12 creates a keystore in my_keystore.pfx.

james-2.3.2 will accept the .keystore but Thunderbird won't and Thunderbird will accept my_keystore.pfx but James throws exceptions. 
I think the error might be in the james-2.3.2/apps/james/SAR-INF/conf.xml file.

<sockets>
  <server-sockets>
    <factory name="plain" class="org.apache.avalon.cornerstone.blocks.sockets.DefaultServerSocketFactory"/>
    <factory name="ssl" class="org.apache.avalon.cornerstone.blocks.sockets.TLSServerSocketFactory">
      <ssl-factory>
        <keystore>
          <file>conf/my_keystore.pfx</file>
          <password>password</password>
          <key-password>password</key-password>
          <type>PKCS12</type>
          <protocol>TLS</protocol>
          <algorithm>SHA256withRSA</algorithm>
          <authenticate-client>false</authenticate-client>
         </keystore>
       </ssl-factory>
     </factory>
   </server-sockets
When i try to run james again from the batch file I get NoSuchAlgorithException. Have tried using allsorts for the algorithm tag instead of SHA256withRSA but nothing seems to work. If anyone knows the answer I'd be greatfull. Also if anyone knows how to turn on the logging so I can see it in the log file that too would be helpfull.

sunjce_provider.jar has to be copied from the JRE lib folder to the lib folder in James. James will then accept the .pfx file if the algorithm tag is left as the default SunX509. I spotted this nugget of information in the comments of the config xml file.
