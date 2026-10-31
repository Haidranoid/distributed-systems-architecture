using PostsService.DTOs;
using PostsService.Models;

namespace PostsService.Mappers;

public static class PostMapper
{
    public static PostDto ToDto(Post post)
    {
        return new PostDto
        {
            Id = post.Id,
            Title = post.Title,
            Content = post.Content
        };
    }

    public static IEnumerable<PostDto> ToDto(IEnumerable<Post> posts)
    {
        return posts.Select(ToDto);
    }
}