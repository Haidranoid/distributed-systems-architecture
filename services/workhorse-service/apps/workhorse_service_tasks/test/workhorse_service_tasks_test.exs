defmodule WorkhorseServiceTasksTest do
  use ExUnit.Case
  doctest WorkhorseServiceTasks

  test "greets the world" do
    assert WorkhorseServiceTasks.hello() == :world
  end
end
