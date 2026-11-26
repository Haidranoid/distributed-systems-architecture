defmodule WorkhorseServiceWeb.JobJSON do
  alias WorkhorseServiceJobs.Jobs.Job

  @doc """
  Renders a list of jobs.
  """
  def index(%{jobs: jobs}) do
    %{data: for(job <- jobs, do: data(job))}
  end

  @doc """
  Renders a single job.
  """
  def show(%{job: job}) do
    %{data: data(job)}
  end

  defp data(%Job{} = job) do
    %{
      id: job.id,
      name: job.name
    }
  end
end
