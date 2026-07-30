using System.ComponentModel.DataAnnotations;

namespace ContentService.Requests;

public class CreatePostRequest
{
    [Required]
    [StringLength(maximumLength: 100, MinimumLength = 4)]
    public string Title { get; set; } =  string.Empty;
    
    [Required]
    [StringLength(maximumLength: 250, MinimumLength = 4)]
    public string Content { get; set; } = string.Empty;
}