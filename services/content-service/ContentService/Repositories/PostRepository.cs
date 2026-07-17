using ContentService.Data;
using ContentService.Models;
using Microsoft.EntityFrameworkCore;

namespace ContentService.Repositories;

public class PostRepository : IPostRepository
{
    private readonly AppDbContext _context;

    public PostRepository(AppDbContext context)
    {
        _context = context;
    }

    public async Task<IEnumerable<Post>> GetAllAsync()
    {
        return await _context.Posts.ToListAsync();
    }

    public async Task<Post?> GetByIdAsync(long id)
    {
        return await _context.Posts.FindAsync(id);
    }

    public async Task<Post> CreateAsync(Post post)
    {
        _context.Posts.Add(post);

        await _context.SaveChangesAsync();

        return post;
    }

    public async Task<Post> UpdateAsync(Post post)
    {
        _context.Posts.Update(post);

        await _context.SaveChangesAsync();

        return post;
    }

    public async Task DeleteAsync(Post post)
    {
        _context.Posts.Remove(post);

        await _context.SaveChangesAsync();
    }
}