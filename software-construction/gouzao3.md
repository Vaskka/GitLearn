# 读取数据库伪代码实现

## 流程图

![流程图](https://raw.githubusercontent.com/Vaskka/GitLearn/master/software-construction/homework-3.png)

## 伪代码

```java
function readFromDatabase(searchCondition) {

    // 获取指定数据库相应表集合的引用
    db -> get db reference with 'Database Name' and 'Collection Name'

    // 获取当前数据库的会话引用
    session -> get from db reference

    // 当数据库不是写状态时，可以读取信息
    if (session.writeLock is false) {

        // 根据查询条件查询指定数据
        // 得到结果的迭代器
        iterator -> use searchCondition get iterable object

        // line 有意义时
        while (line is not NULL) {
            // 为结果集添加一条结果
            resultSet.addOne(line)

            // line 移动到下一条数据
            line -> nextLine
        }
    }
    else {
        // 不可读抛出异常
        throw exception 'database write mutex'
    }

    return resultSet
}
```
