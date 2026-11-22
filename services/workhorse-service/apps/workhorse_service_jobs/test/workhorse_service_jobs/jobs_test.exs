defmodule WorkhorseServiceJobs.JobsTest do
  use WorkhorseServiceJobs.DataCase

  alias WorkhorseServiceJobs.Jobs

  describe "jobs" do
    alias WorkhorseServiceJobs.Jobs.Job

    import WorkhorseServiceJobs.JobsFixtures

    @invalid_attrs %{name: nil, status: nil, started_at: nil, scheduled_at: nil, completed_at: nil}

    test "list_jobs/0 returns all jobs" do
      job = job_fixture()
      assert Jobs.list_jobs() == [job]
    end

    test "get_job!/1 returns the job with given id" do
      job = job_fixture()
      assert Jobs.get_job!(job.id) == job
    end

    test "create_job/1 with valid data creates a job" do
      valid_attrs = %{name: "some name", status: "some status", started_at: ~U[2026-11-21 19:22:00Z], scheduled_at: ~U[2026-11-21 19:22:00Z], completed_at: ~U[2026-11-21 19:22:00Z]}

      assert {:ok, %Job{} = job} = Jobs.create_job(valid_attrs)
      assert job.name == "some name"
      assert job.status == "some status"
      assert job.started_at == ~U[2026-11-21 19:22:00Z]
      assert job.scheduled_at == ~U[2026-11-21 19:22:00Z]
      assert job.completed_at == ~U[2026-11-21 19:22:00Z]
    end

    test "create_job/1 with invalid data returns error changeset" do
      assert {:error, %Ecto.Changeset{}} = Jobs.create_job(@invalid_attrs)
    end

    test "update_job/2 with valid data updates the job" do
      job = job_fixture()
      update_attrs = %{name: "some updated name", status: "some updated status", started_at: ~U[2026-11-22 19:22:00Z], scheduled_at: ~U[2026-11-22 19:22:00Z], completed_at: ~U[2026-11-22 19:22:00Z]}

      assert {:ok, %Job{} = job} = Jobs.update_job(job, update_attrs)
      assert job.name == "some updated name"
      assert job.status == "some updated status"
      assert job.started_at == ~U[2026-11-22 19:22:00Z]
      assert job.scheduled_at == ~U[2026-11-22 19:22:00Z]
      assert job.completed_at == ~U[2026-11-22 19:22:00Z]
    end

    test "update_job/2 with invalid data returns error changeset" do
      job = job_fixture()
      assert {:error, %Ecto.Changeset{}} = Jobs.update_job(job, @invalid_attrs)
      assert job == Jobs.get_job!(job.id)
    end

    test "delete_job/1 deletes the job" do
      job = job_fixture()
      assert {:ok, %Job{}} = Jobs.delete_job(job)
      assert_raise Ecto.NoResultsError, fn -> Jobs.get_job!(job.id) end
    end

    test "change_job/1 returns a job changeset" do
      job = job_fixture()
      assert %Ecto.Changeset{} = Jobs.change_job(job)
    end
  end
end
