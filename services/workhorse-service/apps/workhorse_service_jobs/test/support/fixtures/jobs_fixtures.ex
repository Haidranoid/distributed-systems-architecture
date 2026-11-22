defmodule WorkhorseServiceJobs.JobsFixtures do
  @moduledoc """
  This module defines test helpers for creating
  entities via the `WorkhorseServiceJobs.Jobs` context.
  """

  @doc """
  Generate a job.
  """
  def job_fixture(attrs \\ %{}) do
    {:ok, job} =
      attrs
      |> Enum.into(%{
        completed_at: ~U[2026-11-21 19:22:00Z],
        name: "some name",
        scheduled_at: ~U[2026-11-21 19:22:00Z],
        started_at: ~U[2026-11-21 19:22:00Z],
        status: "some status"
      })
      |> WorkhorseServiceJobs.Jobs.create_job()

    job
  end
end
