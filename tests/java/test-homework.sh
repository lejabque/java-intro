#/bin/bash
set -eu

check_dir() {
    if [[ ! -d "$1" ]] ; then
        echo Directory \"$1\" does not exists
        exit 1
    fi
}

if [[ "${2:-}" == "" ]] ; then
    echo Usage: compile-homework.sh repository-id homework
    exit 1
fi

case "$2" in
    3)
        package=queue
        test=queue.ArrayQueueTest
        args=
        ;;
    4)
        package=queue
        test=queue.QueueTest
        args=
        ;;
    5)
        package=expression/generic
        test=expression.generic.GenericTest
        args=easy
        ;;
    *)
        echo Unknown homework \"$2\"
        echo Supported homeworks:
        echo "   3 Array queue"
        echo "   4 Queues"
        echo "   5 Generic expressions"
        exit 1
esac

base="$(dirname "${BASH_SOURCE[0]}")"
repo="$base/../../$1"
check_dir "$repo"
check_dir "$repo/java-solutions"
check_dir "$repo/java-solutions/$package"

build="$base/_build"
[[ -e "$build" ]] || rm -rf "$build"

sources=$(find "$repo/java-solutions/$package" -name '*.java')

echo javac -encoding utf8 -d "$build" -cp "$base;$repo/java-solutions" "$base/$package/*.java" $sources
     javac -encoding utf8 -d "$build" -cp "$base;$repo/java-solutions" "$base/$package/*.java" $sources

java -ea -cp "$build" "$test" $args

echo SUCCESS