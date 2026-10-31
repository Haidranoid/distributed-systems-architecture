using MediaGenerationService.Data;
using MediaGenerationService.Models;
using Microsoft.EntityFrameworkCore;

namespace MediaGenerationService.Repositories;

public class MediaQueryRepository : IMediaQueryRepository
{
    private readonly AppDbContext _context;

    public MediaQueryRepository(AppDbContext context)
    {
        _context = context;
    }

    public async Task<IEnumerable<MediaQuery>> GetAllAsync()
    {
        return await _context.MediaQueries.ToListAsync();
    }

    public async Task<MediaQuery?> GetByIdAsync(int id)
    {
        return await _context.MediaQueries.FindAsync(id);
    }

    public async Task<MediaQuery> CreateAsync(MediaQuery mediaQuery)
    {
        _context.MediaQueries.Add(mediaQuery);

        await _context.SaveChangesAsync();

        return mediaQuery;
    }

    public async Task<MediaQuery> UpdateAsync(MediaQuery mediaQuery)
    {
        _context.MediaQueries.Update(mediaQuery);

        await _context.SaveChangesAsync();

        return mediaQuery;
    }

    public async Task DeleteAsync(MediaQuery mediaQuery)
    {
        _context.MediaQueries.Remove(mediaQuery);

        await _context.SaveChangesAsync();
    }
}