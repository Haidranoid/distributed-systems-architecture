using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace ContentService.Models;

[Table("posts")]
public class Post
{
    [Column("id")]
    [Key]
    public long Id { get; set; }
    
    [Column("title")]
    [MaxLength(100)]
    public string Title { get; set; } = string.Empty;
    
    [Column("content")]
    [MaxLength(250)]
    public string Content { get; set; } = string.Empty;
}