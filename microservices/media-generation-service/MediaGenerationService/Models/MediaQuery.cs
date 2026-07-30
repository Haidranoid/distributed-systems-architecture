using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MediaGenerationService.Models;

[Table("media_queries")]
public class MediaQuery
{
    [Column("id")]
    [Key]
    public int Id { get; set; }
    
    [Column("positive_prompt")]
    [MaxLength(100)]
    public string PositivePrompt { get; set; } = string.Empty;
}