using MediaGenerationService.Models;
using Microsoft.EntityFrameworkCore;

namespace MediaGenerationService.Data;

//public class AppDbContext(DbContextOptions<AppDbContext> options) : DbContext(options)
public class AppDbContext : DbContext
{
    public AppDbContext(DbContextOptions<AppDbContext> options) : base(options)
    {
    }

    public DbSet<MediaQuery> MediaQueries => Set<MediaQuery>();
}