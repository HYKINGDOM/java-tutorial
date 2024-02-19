package com.java.func.supplier;

public class UserFactory {
    // 部门服务, rpc 接口

    private DepartmentService departmentService;

    //    // 主管服务, rpc 接口
    //    @Resource
    //    private SupervisorService supervisorService;
    //
    //    // 权限服务, rpc 接口
    //    @Resource
    //    private PermissionService permissionService;

    public LazyUser buildUser(long uid) {
        Lazy<String> departmentLazy = Lazy.of(() -> departmentService.getDepartment(uid));
        // 通过部门获得主管
        // department -> supervisor
        //        Lazy<Long> supervisorLazy = departmentLazy.map(
        //                department -> SupervisorService.getSupervisor(department)
        //        );
        //        // 通过部门和主管获得权限
        //        // department, supervisor -> permission
        //        Lazy<Set<String>> permissionsLazy = departmentLazy.flatMap(department ->
        //                supervisorLazy.map(
        //                        supervisor -> permissionService.getPermissions(department, supervisor)
        //                )
        //        );

        LazyUser user = new LazyUser();
        user.setUid(uid);
        user.setDepartment(departmentLazy);
        //user.setSupervisor(supervisorLazy);
        //user.setPermissions(permissionsLazy);
        return user;
    }
}
