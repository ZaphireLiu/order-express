## 参数获取问题

```java
   /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页查询员工")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("员工分页查询，参数：{}", employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }
```

这是一个spring应用里controller层的一个函数，这个写法是没问题的
然而，当我把里面所有的`employeePageQueryDTO`改为`pageQueryDTO`就无法正确获取参数

ai认为是spring boot内部将这个名称和dto对象做了绑定：
```
可能的原因：
- 你的 DTO 类中的字段名与 employeePageQueryDTO 这个参数名有某种关联
- 之前的配置恰好支持了这种参数名的反射
- 代码中可能还有其他配置影响了参数绑定
- 建议使用显式的注解来避免这类问题，这样代码更加清晰和可靠。
```

## SQL位置选择

比较简单的可以用注解，对于动态条件/多表查询等最好还是放xml

## 动态PostMapping

``` java
    @PostMapping("/status/{status}") // ? 大概是直接用状态分成了俩api，怪哎
    @ApiOperation("切换账号状态")
    public Result switchStatus(@PathVariable("status") Integer status, Long id) {
        log.info("账号状态切换 - ID: {}, 状态: {}", id, status);
        employeeService.switchStatus(status, id);
        return Result.success();
    }
```