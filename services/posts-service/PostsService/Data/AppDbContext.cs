using PostsService.Models;
using Microsoft.EntityFrameworkCore;

namespace PostsService.Data;

//public class AppDbContext(DbContextOptions<AppDbContext> options) : DbContext(options)
public class AppDbContext : DbContext
{
    public AppDbContext(DbContextOptions<AppDbContext> options) : base(options)
    {
    }

    public DbSet<Post> Posts => Set<Post>();
}