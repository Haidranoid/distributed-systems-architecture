defmodule WorkhorseJobsTest do
  use ExUnit.Case
  doctest WorkhorseJobs

  test "greets the world" do
    assert WorkhorseJobs.hello() == :world
  end
end
