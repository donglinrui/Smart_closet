# Smart_closet
### 1.测试截图

主页：可以实时查看当前天气，并且下方依据天气信息给出穿衣推荐，并且可以通过delete按钮删除推荐衣物，并且重新推荐衣物。

![image-20210203101510892](https://github.com/donglinrui/Smart_closet/blob/main/README.assets/image-20210203101510892.png)



增加衣物

![image-20210203101737589](https://github.com/donglinrui/Smart_closet/blob/main/README.assets/image-20210203101737589.png)

增加衣物图片

![image-20210203101754870](https://github.com/donglinrui/Smart_closet/blob/main/README.assets/image-20210203101754870.png)

增加衣物属性：

![image-20210203101832567](https://github.com/donglinrui/Smart_closet/blob/main/README.assets/image-20210203101832567.png)

查看已存储衣物信息：在此页面中可以分类查看存储的所有衣物，并且可以对衣物进行编辑和修改。

![image-20210203101901362](https://github.com/donglinrui/Smart_closet/blob/main/README.assets/image-20210203101901362.png)

衣物推荐：在此页面中可以显示各类型衣物剩余数量，并且根据推荐算法计算出用户需要的衣服，然后给出淘宝链接进行衣物推荐。

![image-20210203101930284](https://github.com/donglinrui/Smart_closet/blob/main/README.assets/image-20210203101930284.png)

### 2.系统框架及功能模块

![image-20210203101151090](https://github.com/donglinrui/Smart_closet/blob/main/README.assets/image-20210203101151090.png)

##### 天气预报模块

根据定位信息提供每日天气状况和天气预报，可以通过手动修改定位信息。

##### 穿衣推荐模块

结合每日天气信息与用户穿衣偏好提供合理的穿衣推荐，同时提供用户自主选择的功能，根据用户自主选择以及对推荐衣物的评价反馈进行相关推送指数修改以符合用户习惯。

##### 衣物增减模块

能增加删除用户所属衣物，以照片和分类方式存储记录。

##### 用户增减模块

能在主界面实现用户的增减，体现吾家这一家庭概念。

##### 新服饰推送模块

由用户选择的季节、衣物品类、价格等为关键字推荐符合不同用户风格喜好的衣物。

### 3. 软件设计方案

（1）  总体设计

利用BottomNavigationView和fragment 来构建整个app的框架 通过底部的选项可以自由选择切换到的页面。此种方法减少了在activity 间切换的时间，节约了手机内存，并且使得用户操作更加简单便捷。

（2）  主页

流程图：

![img](https://github.com/donglinrui/Smart_closet/blob/main/README.assets/clip_image002.jpg)

（3）  添加衣物页面

流程图

![img](https://github.com/donglinrui/Smart_closet/blob/main/README.assets/clip_image004.jpg)

（4）  查看衣物页面

架构图

![img](https://github.com/donglinrui/Smart_closet/blob/main/README.assets/clip_image006.jpg)

（5）衣物推荐页面

流程图

![img](https://github.com/donglinrui/Smart_closet/blob/main/README.assets/clip_image008.jpg)