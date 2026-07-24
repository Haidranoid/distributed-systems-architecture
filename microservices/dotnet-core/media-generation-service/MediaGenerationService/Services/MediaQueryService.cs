using MediaGenerationService.DTOs;
using MediaGenerationService.Mappings;
using MediaGenerationService.Models;
using MediaGenerationService.Repositories;
using MediaGenerationService.Requests;

namespace MediaGenerationService.Services;

public class MediaQueryService : IMediaQueryService
{
    private readonly IMediaQueryRepository _repository;

    public MediaQueryService(IMediaQueryRepository repository)
    {
        _repository = repository;
    }
    
    public async Task<IEnumerable<MediaQueryDto>> GetAllAsync()
    {
        var mediaQueries = await _repository.GetAllAsync();

        return MediaQueryMapping.ToDto(mediaQueries);
    }

    public async Task<MediaQueryDto?> GetByIdAsync(int id)
    {
        var mediaQuery = await _repository.GetByIdAsync(id);
        
        if (mediaQuery is null)
            return null;
        
        return MediaQueryMapping.ToDto(mediaQuery);
    }

    public async Task<MediaQueryDto> CreateAsync(CreateMediaQueryRequest createMediaQueryRequest)
    {
        var mediaQueryToCreate = new MediaQuery
        {
            PositivePrompt = createMediaQueryRequest.PositivePrompt,
        };

        var mediaQueryCreated = await _repository.CreateAsync(mediaQueryToCreate);
        
        return MediaQueryMapping.ToDto(mediaQueryToCreate);
    }

    public async Task<MediaQueryDto?> UpdateAsync(int id, UpdateMediaQueryRequest updateMediaQueryRequest)
    {
        var mediaQueryToUpdate = await _repository.GetByIdAsync(id);
        
        if (mediaQueryToUpdate is null)
            return null;
        
        if (updateMediaQueryRequest.PositivePrompt != "")
            mediaQueryToUpdate.PositivePrompt = updateMediaQueryRequest.PositivePrompt;
        
        var mediaQueryUpdated = await _repository.UpdateAsync(mediaQueryToUpdate);
        
        return MediaQueryMapping.ToDto(mediaQueryToUpdate);
    }

    public async Task<bool> DeleteAsync(int id)
    {
        var mediaQueryToDelete = await _repository.GetByIdAsync(id);
        
        if(mediaQueryToDelete is null)
            return false;
        
        await _repository.DeleteAsync(mediaQueryToDelete);
        
        return true;
    }
}