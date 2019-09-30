1. 创建ssh key (用于和远程仓库建立连接)
	ssh-keygen -t rsa -C "your email(一定要是注册github时使用的邮箱)"
	由于本地仓库和github之间连接是用SSH连接的，所以要创建SSH key
	步骤：ssh-keygen -t rsa -C "your email"
	          cd ~  (以覆盖的方式)
	          pwd  查看在哪个文件夹中
	          cd .ssh/    有一个隐藏文件夹ssh,进去
	          ll   查看ssh文件夹中的文件    （github使用的key时公钥，选择id_rsa.pub）
	          cat id_rsa.pub   (会显示生成的key,复制以后到github里粘贴)
	之后可以用 ssh -T git@github.com  查看本地仓库和github是否连通
	
2. 创建本地仓库(可以先创建一个文件夹作为本地仓库 mkdir gitCode(创建一个文件夹gitCode))
	**使用 cd ..   返回上级目录(中间有空格)  或  cd [本地仓库路径]  进入指定文件夹**
	git init   创建一个本地仓库
	
3.  git add [文件名.后缀]    将指定文件推到暂存区
	git add .   将文件夹下所有文件及子文件夹推到暂存区

4. git commit -m "描述信息"
	如果出现	On branch master
			nothing to commit, working tree clean
	说明暂存区没有新内容,不会提交成功
	git commit --allow-empty -m "empty"    进行一次空提交(意义不大)


5. git remote add origin https://github.com/17734812911/仓库名.git     将本地仓库与远程仓库关联起来
		如果出现以下错误:fatal: remote origin already exists.
		解决办法: git remote rm origin
		然后再次执行关联

6. git push -u origin master
		如果出现如下错误：error: failed to push some refs to 'https://github.com/bluetata/ 
		解决办法：使用命令行：git pull --rebase origin master
		该命令的意思是把远程库中的更新合并到（pull=fetch+merge）本地库中,
		--rebase的作用是取消掉本地库中刚刚的commit，并把他们接到更新后的版本库之中。

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
**如果要切换提交的分支(不提交到master中)**
1. git checkout -b [分支名]    在本地新建分支(名字和远程仓库中的分支名相同)
2. git branch -a              查看本地分支
3. git checkout [分支名]         切换到该分支

接着就可以使用
1. ll 查看该分支下有哪些文件或文件夹
2. cd [文件夹]  进入要推送的文件夹(或者直接进行第三步)
3. 进行add及以后的命令
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
**克隆仓库**
1. cd [文件夹名称]   选着要存放的文件夹
2. git clone [github地址.git]
    例：git clone https://github.com/17734812911/java.git
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
**删除仓库中的文件**(需要先将远程仓库中的文件拉下来进行操作)
# 仅删除远程分支中的文件,保留本地文件
	删除远程仓库中的文件filename
	git rm --cached filename
	git commit -m "delete remote file filename "
	git push -u oringin master(此处是当前分支的名字)
# 删除远程文件夹
	删除远程文件夹directoryname
	git rm -r --cached directoryname
	git commit -m "delete remote directory directoryname "
	git push -u oringin master(此处是当前分支的名字)
# 删除本地文件与远程分支中的文件
	删除文件filename
	git rm filename
	git commit -m "delete file filename "
	git push -u oringin master(此处是当前分支的名字)
# 删除本地文件与远程分支中的文件夹
	删除文件夹directoryname
	git rm -r directoryname
	git commit -m "delete directory directoryname "
	git push -u oringin master(此处是当前分支的名字)

========================================================================================================================================================================================
========================================================================================================================================================================================
**Eclipse中使用Git**
1. 推送项目
	在项目名上右键 Team--Share Project--勾选上方Use or create repository in parent folder of project(为项目创建本地仓库)--选中项目点击下方Create Repository(创建本地仓库,右侧为仓库名称)-->
		finish-->在项目名上右键 Team--Add to Index(将项目推送到暂存区)-->在项目名上右键 Team-->commit--填写提交信息-->commit(到此项目已经在本地仓库中)-->
		在github官网上创建仓库"仓库名"(该名称与本地仓库名称一致，表示本地仓库提交到远程仓库并与之关联)-->在项目名上右键 Team-->remote--Push-->从上至下-->
		URL为仓库地址https://github.com/17734812911/java_01.git(填完URL后面的信息会自动出来)-->填写用户名密码(不要勾复选框)-->next-->Source ref中选择分支,Destination ref中选择要推送到的分支-->
		点击Add Spec-->若是勾选"Force Update"复选框,表示覆盖提交(会覆盖远程分支中的内容)-->next-->finish(右下角有推送进度,推送完可以在官网查看)
2. 拉项目
	直接	Team-->pull
========================================================================================================================================================================================
常用命令
pwd  查看当前在哪个目录下

ll   显示目录下的所有文件

ls用来显示目录或具体文件列表
	ls -1：每行列出一个文件，即以单列形式列出
	ls -a：列出所有文件，包括隐藏文件
	ls -la：所有文件的长格式列表（含权限、所有权、大小和修改日期）
	ls -lh：使用人可读单位（KB，MB，GB）显示大小的长格式列表
	ls -lS：按大小排序的长格式列表（降序）
	ls -ltr：按大小排序的长格式列表（升序）
	
cd ..  到上一级目录

git status   查看仓库状态

mkdir   创建新目录 (例：mkdir demo2)

cd 目录名  进入指定目录中

git init    初始化本地仓库

echo    添加文件到版本库 (例：echo "github" >>test.txt   将github追加到test.txt文件里)

cat  展示当前文件的内容(不是文件夹)  (例：cat test.txt)

git remote rm [仓库名]   删除远程仓库
	例：删除仓库origin2
	       git remote rm origin2
		   
git log 列出所有历史记录，最近的排在最上方，显示提交对象的哈希值，作者、提交日期、和提交说明
	git log -n如果不想向上面那样全部显示，可以选择显示前N条
	
git reset --hard 第x次commit号
	将最终仓库和暂存区文件都回滚到第x次提交
	
git rm 文件名
	将本地仓库清理干净(暂存区和仓库区还没有清空，进行一次git commit -m "描述信息",两次git status就行)
	
git push origin :[分支名]
	删除仓库分支