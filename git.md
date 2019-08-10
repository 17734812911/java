一. 创建仓库 （https://www.imooc.com/video/17966）

pwd
查看当前在哪个目录下

ll
显示目录下的所有文件

ls用来显示目录或具体文件列表
	ls -1：每行列出一个文件，即以单列形式列出
	ls -a：列出所有文件，包括隐藏文件
	ls -la：所有文件的长格式列表（含权限、所有权、大小和修改日期）
	ls -lh：使用人可读单位（KB，MB，GB）显示大小的长格式列表
	ls -lS：按大小排序的长格式列表（降序）
	ls -ltr：按大小排序的长格式列表（升序）

cd ..
到上一级目录

mkdir
创建新目录（例：mkdir demo2）

cd 目录名
进入指定目录中

git init
初始化版本

echo 
添加文件到版本库（例：echo "github" >>test.txt   将github追加到test.txt文件里）

cat
展示当前文件的内容（例：cat test.txt）

git add
添加（例：git add test.txt）

git commit -m "描述信息"
为刚添加的文件添加描述信息

git status
查看仓库状态

二. Git工作流  （https://www.imooc.com/video/17967）

clear
清理当前工作目录

git reset HEAD "文件名"
将暂存区的文件回归到工作区

git checkout --文件名
将工作区清理干净（删除文件中最近追加的内容）

git log 列出所有历史记录，最近的排在最上方，显示提交对象的哈希值，作者、提交日期、和提交说明
	git log -n如果不想向上面那样全部显示，可以选择显示前N条

git reset --hard 第x次commit号
将最终仓库和暂存区文件都回滚到第x次提交

git rm 文件名
将本地仓库清理干净（暂存区和仓库区还没有清空，进行一次git commit -m "描述信息",两次git status就行）

三. 远程仓库(github)（https://www.runoob.com/git/git-remote-repo.html）

1. 创建SSH key
ssh-keygen -t rsa -C "your email(一定要是注册github时使用的邮箱)"
由于本地仓库和github之间连接是用SSH连接的，所以要创建SSH key
步骤：ssh-keygen -t rsa -C "your email"
          cd ~  (以覆盖的方式)
          pwd  查看在哪个文件夹中
          cd .ssh/    有一个隐藏文件夹ssh,进去
          ll   查看ssh文件夹中的文件    （github使用的key时公钥，选择id_rsa.pub）
          cat id_rsa.pub   (会显示生成的key,复制以后到github里粘贴)

ssh -T git@github.com
查看本地仓库和github是否连通

2. 查看当前的远程仓库
   git remote
   git remote -v     执行时加上 -v 参数，你还可以看到每个别名的实际链接地址

3. 提取远程仓库
    1、从远程仓库下载新分支与数据
         git fetch
     2、从远端仓库提取数据并尝试合并到当前分支
          git merge
     假设你配置好了一个远程仓库，并且你想要提取更新的数据，你可以首先执
     行 git fetch [仓库名] 告诉 Git 去获取它有你没有的数据，然后你可以执行
     git merge [仓库名]/[分支名] 以将服务器上的任何更新（假设有人这时候推送到服务器
     了）合并到你的当前分支

4. 添加远程仓库
主要有三个命令
     git remote add origin https://github.com/17734812911/java.git
     
     将本地仓库和远程仓库关联起来
     git pull origin master

     git push -u origin master
     例：# 添加仓库 origin2（https://github.com/17734812911/java.git 是远程仓库地址）
            git remote add origin2 https://github.com/17734812911/java.git

5.删除远程仓库
    git remote rm [仓库名]
    例：删除仓库origin2
           git remote rm origin2

6.将本地修改的文件推送到远程仓库
      远程仓库已经有这个文件，直接 git push -u origin master
7. 将新建的文件推送到远程仓库
      远程仓库中没有这个文件

      将文件推到暂存区
      git add [文件名.后缀]

      git commit -m "描述信息"

      git remote add origin https://github.com/17734812911/仓库名.git

      git push (如果之前已经执行过git push -u [仓库名] [分支名],没有的话执行给git pull [仓库名][分支名]) 
           如果出现如下错误：error: failed to push some refs to 'https://github.com/bluetata/ 
	   解决办法：使用命令行：git pull --rebase origin master
	   该命令的意思是把远程库中的更新合并到（pull=fetch+merge）本地库中，
	    --rebase的作用是取消掉本地库中刚刚的commit，并把他们接到更新后的版本库之中。




8. 克隆仓库
    git clone [github仓库页面网址.git]
    例：git clone https://github.com/17734812911/java.git     