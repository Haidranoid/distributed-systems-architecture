using ContentService.DTOs;
using ContentService.Mappings;
using ContentService.Models;
using ContentService.Repositories;
using ContentService.Requests;

namespace ContentService.Services;

public class PostService : IPostService
{
    private readonly IPostRepository _repository;

    public PostService(IPostRepository repository)
    {
        _repository = repository;
    }
    
    public async Task<IEnumerable<PostDto>> GetAllAsync()
    {
        var posts = await _repository.GetAllAsync();

        return PostMapping.ToDto(posts);
    }

    public async Task<PostDto?> GetByIdAsync(long id)
    {
        var post = await _repository.GetByIdAsync(id);
        
        if (post is null)
            return null;
        
        return PostMapping.ToDto(post);
    }

    public async Task<PostDto> CreateAsync(CreatePostRequest createPostRequest)
    {
        var postToCreate = new Post
        {
            Title = createPostRequest.Title,
            Content = createPostRequest.Content
        };

        await _repository.CreateAsync(postToCreate);
        
        return PostMapping.ToDto(postToCreate);
    }

    public async Task<PostDto?> UpdateAsync(long id, UpdatePostRequest updatePostRequest)
    {
        var postToUpdate = await _repository.GetByIdAsync(id);
        
        if (postToUpdate is null)
            return null;
        
        
        if (updatePostRequest.Title != "")
            postToUpdate.Title = updatePostRequest.Title;
        
        if (updatePostRequest.Content != "")
            postToUpdate.Content = updatePostRequest.Content;
        
        
        await _repository.UpdateAsync(postToUpdate);
        
        return PostMapping.ToDto(postToUpdate);
    }

    public async Task<bool> DeleteAsync(long id)
    {
        var postToDelete = await _repository.GetByIdAsync(id);
        
        if(postToDelete is null)
            return false;
        
        await _repository.DeleteAsync(postToDelete);
        
        return true;
    }
}