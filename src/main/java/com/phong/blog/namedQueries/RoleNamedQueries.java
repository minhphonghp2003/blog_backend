package com.phong.blog.namedQueries;

public class RoleNamedQueries {
    public static final String getRoleHaveAction =
            """
                            SELECT r.*, a.name as actionName
                            FROM role r
                            JOIN role_action ra
                            ON r.id = ra.role_id
                            JOIN action a
                            ON a.id = ra.action_id
                            WHERE a.name =:actionName 
                    """;
    public static final String getAllAction =
            """
                    select a.name as actionName, r.name as roleName, a.id as actionId, r.id as roleId
                    from action a
                    join role_action ra 
                    on a.id = ra.action_id 
                    join role r 
                    on ra.role_id = r.id
                    """;
    public static final String getAllActionV2 = """
            select new ActionDTO(a.name as actionName,r.name as roleName,a.id as actionId, r.id as roleId) from Action a 
                        """;
    public static final String assignRoleToUer = """
            insert into user_role (user_id,role_id) values (:userId,:roleId)
            """;
}
