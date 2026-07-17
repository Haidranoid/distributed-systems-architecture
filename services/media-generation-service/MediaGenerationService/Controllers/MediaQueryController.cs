using MediaGenerationService.Requests;
using MediaGenerationService.Services;
using Microsoft.AspNetCore.Mvc;

namespace MediaGenerationService.Controllers;

[ApiController]
[Route("/api/v1/media-queries")]
public class MediaQueryController : ControllerBase
{
    private readonly IMediaQueryService _service;

    public MediaQueryController(IMediaQueryService service)
    {
        _service = service;
    }

    [HttpGet]
    public async Task<IActionResult> GetAll()
    {
        var mediaQueries = await _service.GetAllAsync();

        return Ok(mediaQueries);
    }

    [HttpGet("{id:int}")]
    public async Task<IActionResult> GetById(int id)
    {
        var mediaQuery = await _service.GetByIdAsync(id);

        if (mediaQuery is null)
            return NotFound();

        return Ok(mediaQuery);
    }

    [HttpPost]
    public async Task<IActionResult> Create([FromBody] CreateMediaQueryRequest createMediaQueryRequest)
    {
        var mediaQuery = await _service.CreateAsync(createMediaQueryRequest);
        
        return Ok(mediaQuery);
    }

    [HttpPut("{id:int}")]
    public async Task<IActionResult> Update(int id, [FromBody] UpdateMediaQueryRequest updateMediaQueryRequest)
    {
        var mediaQuery = await _service.UpdateAsync(id, updateMediaQueryRequest);
        
        if (mediaQuery is null)
            return NotFound();
        
        return Ok(mediaQuery);
    }

    [HttpDelete("{id:int}")]
    public async Task<IActionResult> Delete(int id)
    {
        var success = await _service.DeleteAsync(id);
        
        if (!success)
            return NotFound();
        
        return NoContent();
    }
}