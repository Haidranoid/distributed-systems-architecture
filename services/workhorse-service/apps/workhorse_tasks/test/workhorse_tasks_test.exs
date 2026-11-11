defmodule WorkhorseTasksTest do
  use ExUnit.Case
  doctest WorkhorseTasks

  test "greets the world" do
    assert WorkhorseTasks.hello() == :world
  end
end
