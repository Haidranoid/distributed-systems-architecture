using MediaGenerationService.DTOs;
using MediaGenerationService.Requests;

namespace MediaGenerationService.Services;

public interface IMediaQueryService
{
    Task<IEnumerable<MediaQueryDto>> GetAllAsync();
    Task<MediaQueryDto?> GetByIdAsync(int id);
    Task<MediaQueryDto> CreateAsync(CreateMediaQueryRequest createMediaQueryRequest);
    Task<MediaQueryDto?> UpdateAsync(int id, UpdateMediaQueryRequest updateMediaQueryRequest);
    Task<bool> DeleteAsync(int id);
}