using ContentService.DTOs;
using ContentService.Models;

namespace ContentService.Mappings;

public static class PostMapping
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