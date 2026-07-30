using ContentService.DTOs;
using ContentService.Requests;

namespace ContentService.Services;

public interface IPostService
{
    Task<IEnumerable<PostDto>> GetAllAsync();
    Task<PostDto?> GetByIdAsync(long id);
    Task<PostDto> CreateAsync(CreatePostRequest createPostRequest);
    Task<PostDto?> UpdateAsync(long id, UpdatePostRequest updatePostRequest);
    Task<bool> DeleteAsync(long id);
}