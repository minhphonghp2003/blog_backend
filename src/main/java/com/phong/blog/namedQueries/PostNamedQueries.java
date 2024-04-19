package com.phong.blog.namedQueries;

public class PostNamedQueries {
    public static final String QUERY =
            """
                    SELECT * 
                    FROM post 
                    """;
    public static final String allPost =
            """
                    select  p.*, count(reader_id) as 
                    like_count,max(s.view_count) as view_count,max( s.share_count) as share_count 
                    from post p left join post_statistic s on p.statistic_id = s.id left join post_like 
                    l on p.id = l.post_id  where  (:readingListId is null or reading_list_id = :readingListId) 
                    and  (:topicId is null or topic_id = :topicId) and  (:authorId is null or user_id = uuid(:authorId)) 
                    and (:status is null or status=:status)
                    group by p.id
                    """;
}
