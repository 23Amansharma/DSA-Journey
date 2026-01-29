<h2>
  <a href="https://leetcode.com/problems/reverse-integer" target="_blank">
    7. Reverse Integer
  </a>
</h2>

<h3>Medium</h3>
<hr>

<p>
Given a signed 32-bit integer <code>x</code>, return <code>x</code> with its digits reversed.
If reversing <code>x</code> causes the value to go outside the signed 32-bit integer range
<code>[-2<sup>31</sup>, 2<sup>31</sup> - 1]</code>, return <strong>0</strong>.
</p>

<p>
<strong>Note:</strong> The environment does not allow storing 64-bit integers.
</p>

<h4>Example 1</h4>
<pre>
Input:  x = 123
Output: 321
</pre>

<h4>Example 2</h4>
<pre>
Input:  x = -123
Output: -321
</pre>

<h4>Example 3</h4>
<pre>
Input:  x = 120
Output: 21
</pre>

<h4>Constraints</h4>
<ul>
  <li>-2<sup>31</sup> ≤ x ≤ 2<sup>31</sup> - 1</li>
</ul>

<h4>Approach</h4>
<ul>
  <li>Extract digits using modulo and division</li>
  <li>Check overflow before multiplying</li>
  <li>Return 0 if overflow occurs</li>
</ul>

<h4>Complexity</h4>
<ul>
  <li><strong>Time:</strong> O(log₁₀ n)</li>
  <li><strong>Space:</strong> O(1)</li>
</ul>
