using System.ComponentModel.DataAnnotations;

namespace MediaGenerationService.Requests;

public class UpdateMediaQueryRequest
{
    [StringLength(maximumLength: 100, MinimumLength = 4)]
    public string PositivePrompt { get; set; } = string.Empty;
}