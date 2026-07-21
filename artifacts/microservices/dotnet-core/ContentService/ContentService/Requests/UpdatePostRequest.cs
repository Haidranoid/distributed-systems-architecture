using System.ComponentModel.DataAnnotations;

namespace ContentService.Requests;

public class UpdatePostRequest
{
    [StringLength(maximumLength: 100)]
    public string Title { get; set; } = string.Empty;
    
    [StringLength(maximumLength: 250)]
    public string Content { get; set; } = string.Empty;
}