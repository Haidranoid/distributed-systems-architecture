using ContentService.Requests;
using ContentService.Services;
using Microsoft.AspNetCore.Mvc;

namespace ContentService.Controllers;

[ApiController]
[Route("/api/v1/content/posts")]
public class PostsController : ControllerBase
{
    private readonly IPostService _service;

    public PostsController(IPostService service)
    {
        _service = service;
    }

    [HttpGet]
    public async Task<IActionResult> GetAll()
    {
        var posts = await _service.GetAllAsync();

        return Ok(posts);
    }

    [HttpGet("{id:long}")]
    public async Task<IActionResult> GetById(long id)
    {
        var post = await _service.GetByIdAsync(id);

        if (post is null)
            return NotFound();

        return Ok(post);
    }

    [HttpPost]
    public async Task<IActionResult> Create([FromBody] CreatePostRequest createPostRequest)
    {
        var post = await _service.CreateAsync(createPostRequest);
        
        return Ok(post);
    }

    [HttpPut("{id:long}")]
    public async Task<IActionResult> Update(long id, [FromBody] UpdatePostRequest updatePostRequest)
    {
        var post = await _service.UpdateAsync(id, updatePostRequest);
        
        if (post is null)
            return NotFound();
        
        return Ok(post);
    }

    [HttpDelete("{id:long}")]
    public async Task<IActionResult> Delete(long id)
    {
        var success = await _service.DeleteAsync(id);
        
        if (!success)
            return NotFound();
        
        return NoContent();
    }
}