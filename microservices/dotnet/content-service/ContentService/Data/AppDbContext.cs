using ContentService.Models;
using Microsoft.EntityFrameworkCore;

namespace ContentService.Data;

//public class AppDbContext(DbContextOptions<AppDbContext> options) : DbContext(options)
public class AppDbContext : DbContext
{
    public AppDbContext(DbContextOptions<AppDbContext> options) : base(options)
    {
    }

    public DbSet<Post> Posts => Set<Post>();
}