defmodule WorkhorseServiceWeb.JobController do
  use WorkhorseServiceWeb, :controller

  alias WorkhorseServiceJobs.Jobs
  alias WorkhorseServiceJobs.Jobs.Job

  action_fallback WorkhorseServiceWeb.FallbackController

  def index(conn, _params) do
    jobs = Jobs.list_jobs()
    render(conn, :index, jobs: jobs)
  end

  def create(conn, %{"job" => job_params}) do
    with {:ok, %Job{} = job} <- Jobs.create_job(job_params) do
      conn
      |> put_status(:created)
      |> put_resp_header("location", ~p"/api/v1/workhorse-service/jobs/#{job}")
      |> render(:show, job: job)
    end
  end

  def show(conn, %{"id" => id}) do
    job = Jobs.get_job!(id)
    render(conn, :show, job: job)
  end

  def update(conn, %{"id" => id, "job" => job_params}) do
    job = Jobs.get_job!(id)

    with {:ok, %Job{} = job} <- Jobs.update_job(job, job_params) do
      render(conn, :show, job: job)
    end
  end

  def delete(conn, %{"id" => id}) do
    job = Jobs.get_job!(id)

    with {:ok, %Job{}} <- Jobs.delete_job(job) do
      send_resp(conn, :no_content, "")
    end
  end
end
