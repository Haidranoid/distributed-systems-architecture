using MediaGenerationService.Models;

namespace MediaGenerationService.Repositories;

public interface IMediaQueryRepository
{
    Task<IEnumerable<MediaQuery>> GetAllAsync();
    Task<MediaQuery?> GetByIdAsync(int id);
    Task<MediaQuery> CreateAsync(MediaQuery mediaQuery);
    Task<MediaQuery> UpdateAsync(MediaQuery mediaQuery);
    Task DeleteAsync(MediaQuery mediaQuery);
}