defmodule WorkhorseServiceJobsTest do
  use ExUnit.Case
  doctest WorkhorseServiceJobs

  test "greets the world" do
    assert WorkhorseServiceJobs.hello() == :world
  end
end
